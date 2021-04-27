package org.example.wisebitstesttask.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.example.wisebitstesttask.framework.TestHelper.byId;
import static org.example.wisebitstesttask.framework.TestHelper.byXPath;

@Component
@Scope("thread")
public class ItemPage extends BasePage {

    private final By addToBasket = byId("add-to-cart-button");

    private final By declineExtraProtection = byXPath("//*[@id='attachSiNoCoverage']//input");

    private final By viewCartButton = byXPath("//*[@id='attach-sidesheet-view-cart-button']//input");

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    /***
     * Add the item currently opened to the cart
     */
    //@Step("Add opened item to the cart")
    public void addOpenedItemToCart() {
        driver.findElement(addToBasket).click();
    }

    /***
     * Click decline for the offer of the extra protection
     */
    //@Step("Decline extra protection")
    public void declineExtraProtection() {
        driver.findElement(declineExtraProtection).click();
    }

    /***
     * Open the cart after adding the item to the cart
     */
    //@Step("Open the cart after adding the item to the cart")
    public void openCart() {
        driver.findElement(viewCartButton).click();
    }
}
