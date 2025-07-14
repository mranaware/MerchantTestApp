package utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
	public static String capture(WebDriver driver, String screenshotName) {
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String path = "./screenshots/" + screenshotName + "_" + System.currentTimeMillis() + ".png";
			File dest = new File(path);
			FileUtils.copyFile(src, dest);
			return path;
		} catch (Exception e) {
			System.err.println("Screenshot capture failed: " + e.getMessage());
			return "";
		}
	}
}
