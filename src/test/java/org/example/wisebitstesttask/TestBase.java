package org.example.wisebitstesttask;

import com.googlecode.junittoolbox.ParallelRunner;
import org.example.wisebitstesttask.framework.Config;
import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

@RunWith(ParallelRunner.class)
@SpringBootTest(
        classes = WisebitsTestTaskApplication.class
)
public abstract class TestBase {

    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    protected WebDriver driver;

    @Autowired
    private ApplicationContext context;

    @Autowired
    protected Config config;

    @Before
    public void setUpDriver(){
        driver.navigate().to(config.getAppFullUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
