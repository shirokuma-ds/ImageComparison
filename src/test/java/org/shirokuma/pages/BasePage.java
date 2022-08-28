package org.shirokuma.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.shirokuma.locator.PageLocator;
import org.shirokuma.utils.WebElementUtility;

import java.time.Duration;

;

public abstract class BasePage {

    private static WebDriver driver;

    public static void initDriver() {
        driver = new ChromeDriver();
    }

    public static WebDriver getDriver() {
        if (driver == null){
            initDriver();
        }
        return driver;
    }

    public abstract PageLocator getLocator();

    public void open(String URL) {
        getDriver().get(URL);
    }

    public WebElement getElement(String fieldName) {
        By locator = WebElementUtility.getLocator(getLocator(), fieldName);
        return new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void click(String fieldName) {
        getElement(fieldName).click();
    }

    public void typeInto(String fieldName, String text) {
        getElement(fieldName).sendKeys(text);
    }

}
