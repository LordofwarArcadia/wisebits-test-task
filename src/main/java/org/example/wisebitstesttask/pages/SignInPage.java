package org.example.wisebitstesttask.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.example.wisebitstesttask.framework.TestHelper.*;

@Component
@Scope("thread")
public class SignInPage extends BasePage {

    private final By loginField = byId("ap_email");

    private final By passwordField = byId("ap_password");

    private final By continueButton = byId("continue");

    private final By signInButton = byId("signInSubmit");

    private final By approvalMsg = byClassName("transaction-approval-word-break");

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    /***
     * Sign in to the account with test user credentials
     * @param login - login of the test user
     * @param password - password of the test user
     */
    public void signInWithCredentials(String login, String password) {
        driver.findElement(loginField).sendKeys(login);
        waitForMilliSeconds(3000);
        driver.findElement(continueButton).click();

        driver.findElement(passwordField).sendKeys(password);
        waitForMilliSeconds(3000);
        driver.findElement(signInButton).click();

        try {
            driver.findElement(approvalMsg);
            //ToDo::add getting link from email
            // and open in in headless browser (may be?)
        } catch (NoSuchElementException e) {
            // intentionally empty because we didn't catch approval window
        }
    }
}
