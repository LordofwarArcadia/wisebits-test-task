package org.example.wisebitstesttask.pages;

import org.example.wisebitstesttask.sections.HeaderSection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.example.wisebitstesttask.framework.TestHelper.byXPath;

@Component
@Scope("thread")
public class CartPage extends BasePage {

    private HeaderSection header;

    //This selector is not effective and clear-looking for any complex logic
    // (like 'delete all but first', or 'delete 2,5,7 items from cart)
    // but it's the simplest solution which gives an ability to rule the logic using the code but not the selector
    private final By deleteItemPartial = byXPath("//input[contains(@name,'submit.delete')]");

    public CartPage(WebDriver driver, HeaderSection header) {
        super(driver);
        this.header = header;
    }

    public HeaderSection header() {
        return header;
    }

    /***
     * Remove the Nth element from the cart (starts from 1, not from 0)
     * @param positionInCart - position of the element in the cart
     */
    //@Step("Remove the {0} item from the cart")
    public void removeTheNthItem(Integer positionInCart) {
        var deleteLinks = driver.findElements(deleteItemPartial);
        deleteLinks.get(positionInCart - 1).click();
    }
}
