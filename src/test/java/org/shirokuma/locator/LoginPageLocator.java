package org.shirokuma.locator;

import org.openqa.selenium.By;

public class LoginPageLocator {
    public By username_field = By.xpath("//input[@class='form__input login__username']");
    public By password_field = By.xpath("//input[@class='form__input login__password']");
}
