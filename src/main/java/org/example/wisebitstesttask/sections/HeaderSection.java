package org.example.wisebitstesttask.sections;

import org.example.wisebitstesttask.pages.SignInPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.example.wisebitstesttask.framework.TestHelper.*;

@Component
@Scope("thread")
public class HeaderSection extends BaseSection {

    private final By accountMenu = byId("nav-link-accountList");

    private final By signInMenuPoint = byPartialLinkText("Sign in");

    private final By signOutMenuPoint = byId("nav-item-signout");

    private final By searchField = byId("twotabsearchtextbox");

    public HeaderSection(WebDriver driver) {
        super(driver);
    }

    /***
     * Sign in to the account with test user credentials
     * @param login - login of the test user
     * @param password - password of the test user
     */
    //@Step("Sign in as {0} with password {1}")
    public void signInToAccount(String login, String password) {
        hoverOverElementBy(driver, accountMenu);
        waitForMilliSeconds(500);
        driver.findElement(signInMenuPoint).click();
        new SignInPage(driver).signInWithCredentials(login, password);
    }

    /***
     * Enter the search query into the search field and fire submit
     * @param searchText
     */
    //@Step("Enter a search query {0} into the search field")
    public void enterSearchTextAndSubmit(String searchText) {
        var search = driver.findElement(searchField);
        search.sendKeys(searchText);
        search.submit();
    }

    /***
     * Sign out from the account
     */
    //@Step("Sign out")
    public void signOut() {
        hoverOverElementBy(driver, accountMenu);
        waitForMilliSeconds(500);
        driver.findElement(signOutMenuPoint).click();
    }
}
