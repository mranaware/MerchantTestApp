package mobile;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import utils.ExcelUtil;
import utils.ScreenshotUtil;

public class LoginMobileSuccessTest extends MobileBaseTest {

	@Test
	public void testLoginSuccess() throws IOException {
		test = extent.createTest("Mobile TC 002: Valid Login");

		try {
			driver.findElement(AppiumBy.accessibilityId("input-username")).sendKeys("VALID_MOBILE");
			driver.findElement(AppiumBy.accessibilityId("input-password")).sendKeys("VALID_PASSWORD");
			driver.findElement(AppiumBy.accessibilityId("button-login")).click();

			boolean isDashboard = driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='Dashboard']"))
					.size() > 0;

			if (isDashboard) {
				test.pass("Successfully logged in.");
				ExcelUtil.updateResult("MOB_TC_002", "Login successful", "Pass", "", reportPath);
			} else {
				String ss = ScreenshotUtil.capture(driver, "MobileLoginSuccessFail");
				test.fail("Dashboard not visible.");
				ExcelUtil.updateResult("MOB_TC_002", "Dashboard missing", "Fail", ss, reportPath);
				Assert.fail();
			}
		} catch (Exception e) {
			String ss = ScreenshotUtil.capture(driver, "Exception_ValidLogin");
			test.fail("Crash: " + e.getMessage());
			ExcelUtil.updateResult("MOB_TC_002", "Exception: " + e.getMessage(), "Fail", ss, reportPath);
			Assert.fail(e.getMessage());
		}
	}
}
