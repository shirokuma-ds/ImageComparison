package org.shirokuma.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.shirokuma.utils.WebElementUtility;

import java.time.Duration;

;

public class BasePage {

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

    public void open(String URL) {
        getDriver().get(URL);
    }

    public WebElement getElement(Object obj, String fieldName) {
        By locator = WebElementUtility.getLocator(obj, fieldName);
        return new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void click(Object obj, String fieldName) {
        getElement(obj, fieldName).click();
    }

    public void typeInto(Object obj, String fieldName, String text) {
        getElement(obj, fieldName).sendKeys(text);
    }

}
