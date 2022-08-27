package org.shirokuma.pages;

import lombok.Getter;
import org.shirokuma.locator.HomePageLocator;

@Getter
public class HomePage extends BasePage {

    public final static String URL = "https://www.blibli.com";
    private final HomePageLocator locator = new HomePageLocator();

    public void open() {
        open(URL);
        getDriver().manage().window().maximize();
    }

}
