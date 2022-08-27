package org.shirokuma.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.shirokuma.pages.BasePage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImageUtility {

    public static byte[] toByteArray(BufferedImage bi, String format) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bi, "png", baos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return baos.toByteArray();
    }

    public static BufferedImage getFullPageScreenshot(String screenShotName, String fileType, String targetDir) {
        StringBuilder sb = new StringBuilder(System.getProperty("user.dir"));
        if(!targetDir.startsWith("/") && !targetDir.startsWith("\\"))
            sb.append("\\");
        sb.append(targetDir).append("\\").append(screenShotName).append(".").append(fileType.toLowerCase());

        File screenshot = ((TakesScreenshot) BasePage.getDriver()).getScreenshotAs(OutputType.FILE);
        File imageFile = new File(sb.toString());
        try {
            FileUtils.copyFile(screenshot, imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return bufferedImage;
    }

}
