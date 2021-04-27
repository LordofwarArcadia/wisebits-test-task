package org.example.wisebitstesttask.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.wisebitstesttask.framework.TestHelper.byClassName;
import static org.example.wisebitstesttask.framework.TestHelper.byXPath;

@Component
@Scope("thread")
public class SearchResultPage extends BasePage {

    private final By searchResultsList = byXPath("//div[contains(@data-component-type,'s-search-result')]");

    private final By innerHeader = byXPath(".//h2//span");

    private final By itemLink = byXPath(".//h2/a");

    private final By priceBlockOfTheItem = byClassName("a-price");

    private final By priceSymbol = byClassName("a-price-symbol");

    private final By priceTotal = byClassName("a-offscreen");

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    /***
     * Find and open the best matched item in Amazon. May throw RuntimeException if no matches found.
     * @param searchString - string to search
     * @param stopWords - stop words which shouldn't be in any found item
     */
    //@Step("Find the best result and open it")
    public void findBestResultForSearchString(String searchString, String[] stopWords) {
        var keyWords = searchString.toLowerCase().split(" ");
        var validResults = driver.findElements(searchResultsList);
        List<Integer> matchedIndexes = new ArrayList<>();

        for (int i = 0; i < validResults.size(); i++) {
            var resultHeader = validResults.get(i).findElement(innerHeader).getText();
            if (matchKeyWords(keyWords, stopWords, resultHeader)) {
                matchedIndexes.add(i);
            }
        }

        if (matchedIndexes.size() == 0) {
            throw new RuntimeException("No appropriate items were found. Further testing has no sense.");
        }
        var bestMatch = getBestPriceIndex(validResults, matchedIndexes);
        bestMatch.findElement(itemLink).click();
    }

    /***
     * Check the search result header for key words from the search query and stop words defined by test
     * @param keyWords - words to find in the search result header
     * @param stopWords - words which shouldn't be in the search result header
     * @param searchHeader - search result header itself
     * @return True if no stop words were found and all key words are in the result header
     */
    private boolean matchKeyWords(String[] keyWords, String[] stopWords, String searchHeader) {
        var loweredHeader = searchHeader.toLowerCase();
        var currentMatches = (int) Arrays.stream(keyWords).filter(loweredHeader::contains).count();
        var foundStopWords = (int) Arrays.stream(stopWords).filter(loweredHeader::contains).count();
        return foundStopWords == 0 && currentMatches == keyWords.length;
    }

    /***
     * Find the best price result
     * @param searchResults - list of WebElements represents the search result
     * @param matchedIndexes - list of found indexes matched for the search query and stop words
     * @return the main web element of the first the cheapest item
     */
    private WebElement getBestPriceIndex(List<WebElement> searchResults, List<Integer> matchedIndexes) {
        float minPrice = Float.MAX_VALUE;
        var bestPriceIndex = 0;

        for (var index : matchedIndexes) {
            var match = searchResults.get(index);

            //Get the price of the item
            var priceBlock = match.findElement(priceBlockOfTheItem);
            var total = priceBlock.findElement(priceTotal).getAttribute("innerHTML");
            var symbol = priceBlock.findElement(priceSymbol).getText();

            float totalPrice = Float.parseFloat(total.replace(symbol, ""));
            if (totalPrice < minPrice) {
                minPrice = totalPrice;
                bestPriceIndex = index;
            }
        }
        return searchResults.get(bestPriceIndex);
    }
}
