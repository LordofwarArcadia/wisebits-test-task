package org.example.wisebitstesttask;

import org.example.wisebitstesttask.framework.TestHelper;
import org.example.wisebitstesttask.pages.CartPage;
import org.example.wisebitstesttask.pages.HomePage;
import org.example.wisebitstesttask.pages.ItemPage;
import org.example.wisebitstesttask.pages.SearchResultPage;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ShopCartTests extends TestBase {

    @Autowired
    private HomePage homePage;

    @Autowired
    private SearchResultPage searchResultPage;

    @Autowired
    private ItemPage itemPage;

    @Autowired
    private CartPage cartPage;

    @Test
    public void checkAddingItemToCartTest() {
        //Arrange
        var searchString = "iphone 11 256Gb black";
        var stopWords = new String[]{"pro", "max", "mini", "renewed"};

        //Act
        homePage.header().signInToAccount(config.getUserLogin(), config.getUserPassword());
        TestHelper.waitForMilliSeconds(20*1000);
        homePage.header().enterSearchTextAndSubmit(searchString);

        searchResultPage.findBestResultForSearchString(searchString, stopWords);

        itemPage.addOpenedItemToCart();
        itemPage.declineExtraProtection();
        itemPage.openCart();

        cartPage.removeTheNthItem(1);
        cartPage.header().signOut();

        //Assert
        //Really don't know what should be asserted here, lol) it's not a common AAA test but just a selenium user story
        //Hm, well, one assertion is possible! We successfully logged out without any Selenium exception - autoassert!
    }

    @Test
    @Ignore
    public void checkAddingItemToCartTestParallel() {
        homePage.header().signInToAccount(config.getUserLogin(), config.getUserPassword());
        homePage.header().enterSearchTextAndSubmit("iphone 12 256Gb black");
    }
}
