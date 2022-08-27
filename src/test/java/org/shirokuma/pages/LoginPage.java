package org.shirokuma.pages;

import lombok.Getter;
import org.shirokuma.locator.LoginPageLocator;

@Getter
public class LoginPage extends BasePage {
    private final LoginPageLocator locator = new LoginPageLocator();
}
