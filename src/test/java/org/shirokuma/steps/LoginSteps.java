package org.shirokuma.steps;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import com.github.romankh3.image.comparison.model.Rectangle;
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
import java.util.Arrays;

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
        homePage.click("login_button");
    }

    @Then("verify that the login page UI is as expected with UI requirement {int}")
    public void verifyThatTheLoginPageIsAsExpected(int req) {
        Assert.assertTrue(loginPage.getElement("username_field").isDisplayed());
        Assert.assertTrue(loginPage.getElement("password_field").isDisplayed());

        BufferedImage actualImage = ImageUtility.getFullPageScreenshot("login_page_screenshot", "png", "target");
        StringBuilder sb = new StringBuilder(System.getProperty("user.dir"));
        sb.append("\\src\\test\\resources\\expectedImages");
        sb.append(String.format("\\login_page_expected_screenshot_%d.png", req));
        BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources(sb.toString());

        switch (req) {
            case 1:

                imageComparisonResult = compareOriginalBlibliLoginPage(expectedImage, actualImage);
                break;
            case 2:
                imageComparisonResult = compareBlibliLoginPageWithoutGoogleAndFacebookExpected(expectedImage, actualImage);
                break;
            case 3:
                imageComparisonResult = compareBlibliLoginPageWithoutGoogleAndFacebook(expectedImage, actualImage);
                break;
            case 4:
                imageComparisonResult = compareWithBlibliImageMove(expectedImage, actualImage, 5.0);
                break;
            case 5:
                imageComparisonResult = compareWithBlibliImageMove(expectedImage, actualImage, 10.0);
                break;
        }

        Assert.assertEquals(ImageComparisonState.MATCH, imageComparisonResult.getImageComparisonState());
    }

    private ImageComparisonResult compareOriginalBlibliLoginPage(BufferedImage expected, BufferedImage actual) {
        ImageComparison imageComparison = new ImageComparison(expected, actual);
        return imageComparison.compareImages();
    }

    private ImageComparisonResult compareBlibliLoginPageWithoutGoogleAndFacebook(BufferedImage expected, BufferedImage actual) {
        Rectangle rectFacebook = ImageUtility.getRectangle(loginPage.getElement("facebook_button"));
        Rectangle rectGoogle = ImageUtility.getRectangle(loginPage.getElement("google_button"));
        ImageComparison imageComparison = new ImageComparison(expected, actual)
//                .setExcludedAreas(List.of(new Rectangle(1040, 528, 1458, 595)))
                .setExcludedAreas(Arrays.asList(rectFacebook, rectGoogle))
                .setExcludedRectangleFilling(true, 75)
                .setDrawExcludedRectangles(true);
        return imageComparison.compareImages();
    }

    private ImageComparisonResult compareBlibliLoginPageWithoutGoogleAndFacebookExpected(BufferedImage expected, BufferedImage actual) {
        ImageComparison imageComparison = new ImageComparison(expected, actual);
        return imageComparison.compareImages();
    }

    private ImageComparisonResult compareWithBlibliImageMove(BufferedImage expected, BufferedImage actual, Double allowPercentage) {
        ImageComparison imageComparison = new ImageComparison(expected, actual)
                .setAllowingPercentOfDifferentPixels(allowPercentage);
        return imageComparison.compareImages();
    }

    @After(order = 1)
    public void afterLogin(Scenario scenario) {
        if (imageComparisonResult != null) {
            byte[] bytes = ImageUtility.toByteArray(imageComparisonResult.getResult(), "png");
            scenario.attach(bytes, "image/png", "Login Page Comparison Screenshot");
            StringBuilder sb = new StringBuilder(System.getProperty("user.dir"));
            sb.append("\\target");
        }
    }
}
