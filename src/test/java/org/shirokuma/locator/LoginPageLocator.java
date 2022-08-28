package org.shirokuma.locator;

import org.openqa.selenium.By;

public class LoginPageLocator {
    public final By username_field = By.xpath("//input[@class='form__input login__username']");
    public final By password_field = By.xpath("//input[@class='form__input login__password']");
    public final By facebook_button = By.xpath("//div[@class='login__third-party-facebook']");
    public final By google_button = By.xpath("//div[@class='login__third-party-google btn__g-login']");
}
