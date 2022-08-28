package org.shirokuma.locator;

import org.openqa.selenium.By;

public class HomePageLocator implements PageLocator {
    public final By login_button = By.xpath("//*[@class='blu-btn btn__login b-outline b-small']");
}
