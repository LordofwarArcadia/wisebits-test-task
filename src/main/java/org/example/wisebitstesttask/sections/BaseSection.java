package org.example.wisebitstesttask.sections;

import org.openqa.selenium.WebDriver;

public abstract class BaseSection {

    protected WebDriver driver;

    public BaseSection(WebDriver driver) {
        this.driver = driver;
    }

}
