package org.example.wisebitstesttask.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableAutoConfiguration(exclude = {
    TransactionAutoConfiguration.class, AopAutoConfiguration.class, CodecsAutoConfiguration.class,
    GsonAutoConfiguration.class, RestTemplateAutoConfiguration.class,
    TaskExecutionAutoConfiguration.class, TaskSchedulingAutoConfiguration.class,
    DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class
})
public class Config {

    @Value("${test.app.host}")
    private String appHostName;

    public String getAppFullUrl() {
        return appHostName;
    }

    @Value("${test.browser}")
    private String browser;

    @Value("${test.browser.chrome.path}")
    private String chromePath;

    //region test user data
    @Value("${test.app.user.login}")
    private String testUserLogin;

    public String getUserLogin() {
        return testUserLogin;
    }

    @Value("${test.app.user.password}")
    private String testUserPassword;

    public String getUserPassword() {
        return testUserPassword;
    }
    //endregion

    @Bean(destroyMethod = "quit")
    @Scope("thread")
    public WebDriver createDriver() {
        switch (browser){
            case "remote":
                //break; //ToDo: uncomment when the implementation will be ready
            case "firefox":
                //break; //ToDo: uncomment when the implementation will be ready
            case "safari":
                //break; //ToDo: uncomment when the implementation will be ready
            case "chrome":
            default:
                return createChromeDriver();

        }
    }

    private ChromeDriver createChromeDriver(){
        System.setProperty("webdriver.chrome.driver", chromePath);
        var driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver;
    }
}
