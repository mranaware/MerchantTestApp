package mobile;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import utils.ExcelUtil;
import utils.ScreenshotUtil;

public class ForgotPasswordMobileTest extends MobileBaseTest {

	@Test
	public void testForgotPasswordFlow() throws IOException {
		test = extent.createTest("Mobile TC 003: Forgot Password");

		try {
			driver.findElement(AppiumBy.accessibilityId("forgot-password")).click();
			driver.findElement(AppiumBy.accessibilityId("input-forgot-username")).sendKeys("9999999999");
			driver.findElement(AppiumBy.accessibilityId("send-btn")).click();

			boolean errorVisible = driver
					.findElements(AppiumBy.xpath("//*[contains(@text, 'Username does not exist')]")).size() > 0;

			if (errorVisible) {
				test.pass("Error shown for non-existing user.");
				ExcelUtil.updateResult("MOB_TC_003", "Forgot error validated", "Pass", "", reportPath);
			} else {
				String ss = ScreenshotUtil.capture(driver, "ForgotPasswordFail");
				test.fail("Expected error not visible.");
				ExcelUtil.updateResult("MOB_TC_003", "Forgot error missing", "Fail", ss, reportPath);
				Assert.fail();
			}
		} catch (Exception e) {
			String ss = ScreenshotUtil.capture(driver, "Exception_ForgotPassword");
			test.fail("Crash: " + e.getMessage());
			ExcelUtil.updateResult("MOB_TC_003", "Exception: " + e.getMessage(), "Fail", ss, reportPath);
			Assert.fail(e.getMessage());
		}
	}
}
