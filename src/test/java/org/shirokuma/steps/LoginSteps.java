package org.shirokuma.steps;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.shirokuma.pages.HomePage;
import org.shirokuma.pages.LoginPage;
import org.shirokuma.utils.ImageUtility;

import java.awt.image.BufferedImage;

public class LoginSteps {

    public LoginSteps(HomePage homePage, LoginPage loginPage) {
        this.homePage = homePage;
        this.loginPage = loginPage;
    }

    private HomePage homePage;
    private LoginPage loginPage;
    private ImageComparisonResult imageComparisonResult;

    @Given("user is on Blibli.com website")
    public void userIsOnBlibliComWebsite() {
        homePage.open();
    }

    @When("user go to the login page")
    public void userGoToTheLoginPage() {
        homePage.click(homePage.getLocator(), "login_button");
    }

    @Then("verify that the login page is as expected")
    public void verifyThatTheLoginPageIsAsExpected() {
        loginPage.typeInto(loginPage.getLocator(), "username_field", "asdasd");
        loginPage.typeInto(loginPage.getLocator(), "password_field", "gqwerqwe");
        Assert.assertTrue(loginPage.getElement(loginPage.getLocator(), "username_field").isDisplayed());
        Assert.assertTrue(loginPage.getElement(loginPage.getLocator(), "password_field").isDisplayed());
        BufferedImage actualImage = ImageUtility.getFullPageScreenshot("login_page_screenshot", "png", "target");
        StringBuilder sb = new StringBuilder(System.getProperty("user.dir"));
        sb.append("\\src\\test\\resources\\expectedImages\\login_page_expected_screenshot.png");
        BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources(sb.toString());
        ImageComparison imageComparison = new ImageComparison(expectedImage, actualImage);
        imageComparisonResult = imageComparison.compareImages();
        Assert.assertEquals(ImageComparisonState.MATCH, imageComparisonResult.getImageComparisonState());
    }

    @After(order = 1)
    public void afterLogin(Scenario scenario) {
        System.out.println("After Login Called");
        if (scenario.isFailed() && imageComparisonResult != null) {
            byte[] bytes = ImageUtility.toByteArray(imageComparisonResult.getResult(), "png");
            scenario.attach(bytes, "image/png", "Login Page Comparison Failed Screenshot");
            StringBuilder sb = new StringBuilder(System.getProperty("user.dir"));
            sb.append("\\target");

        }
    }
}
