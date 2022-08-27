package org.shirokuma.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.shirokuma.pages.BasePage;

public class DriverHook {

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void init() {
        BasePage.initDriver();
    }

    @After
    public void tearDown() {
        if (BasePage.getDriver() != null)
            BasePage.getDriver().quit();
    }

}
