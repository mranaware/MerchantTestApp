package mobile;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import utils.ExcelUtil;
import utils.ScreenshotUtil;

public class LoginMobileTest extends MobileBaseTest {

	@Test
	public void testLoginInvalidCredentials() throws IOException {
		test = extent.createTest("Mobile TC 001: Invalid Login");

		try {
			driver.findElement(AppiumBy.accessibilityId("input-username")).sendKeys("9999999999");
			driver.findElement(AppiumBy.accessibilityId("input-password")).sendKeys("wrongpassword");
			driver.findElement(AppiumBy.accessibilityId("button-login")).click();

			boolean toastVisible = driver.findElements(AppiumBy.xpath("//*[contains(@text, 'Invalid')]")).size() > 0;

			if (toastVisible) {
				test.pass("Toast shown on invalid login.");
				ExcelUtil.updateResult("MOB_TC_001", "Toast shown", "Pass", "", reportPath);
			} else {
				String screenshot = ScreenshotUtil.capture(driver, "MobileInvalidLoginFail");
				test.fail("Toast NOT shown.");
				ExcelUtil.updateResult("MOB_TC_001", "Toast missing", "Fail", screenshot, reportPath);
				Assert.fail();
			}
		} catch (Exception e) {
			String ss = ScreenshotUtil.capture(driver, "Exception_InvalidLogin");
			test.fail("Crash: " + e.getMessage());
			ExcelUtil.updateResult("MOB_TC_001", "Exception: " + e.getMessage(), "Fail", ss, reportPath);
			Assert.fail(e.getMessage());
		}
	}
}
