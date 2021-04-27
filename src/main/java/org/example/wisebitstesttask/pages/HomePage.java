package org.example.wisebitstesttask.pages;

import org.example.wisebitstesttask.sections.HeaderSection;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("thread")
public class HomePage extends BasePage {

    private HeaderSection header;

    public HomePage(WebDriver driver, HeaderSection header) {
        super(driver);
        this.header = header;
    }

    public HeaderSection header() {
        return header;
    }
}
