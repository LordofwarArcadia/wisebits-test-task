package org.example.wisebitstesttask.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TestHelper {

    //region short selectors
    public static By.ByXPath byXPath(String locator) {
        return new By.ByXPath(locator);
    }

    public static By.ByCssSelector byCss(String locator) {
        return new By.ByCssSelector(locator);
    }

    public static By.ById byId(String locator) {
        return new By.ById(locator);
    }

    public static By.ByClassName byClassName(String locator) {
        return new By.ByClassName(locator);
    }

    public static By.ByPartialLinkText byPartialLinkText(String locator) {
        return new By.ByPartialLinkText(locator);
    }

    public static By.ByLinkText byLinkText(String locator) {
        return new By.ByLinkText(locator);
    }

    public static By.ByName byName(String locator) {
        return new By.ByName(locator);
    }

    public static By.ByTagName byTagName(String locator) {
        return new By.ByTagName(locator);
    }
    //endregion

    /***
     * Method for waiting for the selected amount of milliseconds.
     * Throws {@link RuntimeException} if something went wrong.
     * @param milliseconds - amount of milliseconds to wait.
     */
    public static void waitForMilliSeconds(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * Imitate a mouseover action for an element found by a selector.
     * May throw a {@link org.openqa.selenium.NoSuchElementException}.
     * @param driver - {@link org.openqa.selenium.WebDriver} instance.
     * @param by - {@link org.openqa.selenium.By} selector for the element.
     */
    public static void hoverOverElementBy(WebDriver driver, By by) {
        Actions action = new Actions(driver);
        WebElement we = driver.findElement(by);
        action.moveToElement(we).build().perform();
    }
}
