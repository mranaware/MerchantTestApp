package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtil;
import utils.ScreenshotUtil;

public class LoginTests extends BaseTest {

	private final String baseUrl = "https://merchant1.uatdev.in/auth/login";
	private final String username = "9999999999";
	private final String password = "Mansi";

	@Test(priority = 1)
	public void testPageLoadPerformance() throws IOException {
		long start = System.currentTimeMillis();
		driver.get(baseUrl);
		LoginPage lp = new LoginPage(driver);
		boolean loaded = lp.waitForLoginElements();
		long end = System.currentTimeMillis();
		long duration = end - start;

		test = extent.createTest("Test Case 1: Page Load Performance");
		test.info("Page load time: " + duration + " ms");

		if (loaded && duration < 30000) {
			test.pass("Login elements loaded within acceptable time");
			ExcelUtil.updateResult("TC_001", "Loaded in " + duration + " ms", "Pass", "", reportPath);
		} else {
			String screenshot = ScreenshotUtil.capture(driver, "PageLoadFail");
			test.fail("Elements did not load within 30 seconds. Time: " + duration + " ms");
			ExcelUtil.updateResult("TC_001", "Page load too slow or failed", "Fail", screenshot, reportPath);
			Assert.fail();
		}
	}

	@Test(priority = 2)
	public void testInvalidLogin() throws IOException {
		driver.get(baseUrl);
		LoginPage lp = new LoginPage(driver);
		lp.login(username, password);

		test = extent.createTest("Test Case 2: Invalid Login");
		boolean errorVisible = lp.isErrorToastVisible();

		if (errorVisible) {
			test.pass("Error toast shown on invalid login.");
			ExcelUtil.updateResult("TC_002", "Invalid login handled correctly", "Pass", "", reportPath);
		} else {
			String screenshot = ScreenshotUtil.capture(driver, "InvalidLoginFail");
			test.fail("Error toast NOT shown.");
			ExcelUtil.updateResult("TC_002", "Invalid login error not visible", "Fail", screenshot, reportPath);
			Assert.fail();
		}
	}

	@Test(priority = 3)
	public void testForgotPasswordFlow() throws IOException {
		driver.get(baseUrl);
		LoginPage lp = new LoginPage(driver);

		test = extent.createTest("Test Case 3: Forgot Password");

		if (lp.isForgotPasswordVisible()) {
			lp.clickForgotPassword();
			lp.sendForgotUsername(username);

			if (lp.isUserNotExistVisible()) {
				test.pass("Correct message shown: Username does not exist");
				ExcelUtil.updateResult("TC_003", "Non-existing user message shown", "Pass", "", reportPath);
			} else {
				String screenshot = ScreenshotUtil.capture(driver, "ForgotPasswordFail");
				test.fail("Expected error message not shown");
				ExcelUtil.updateResult("TC_003", "Forgot password failed", "Fail", screenshot, reportPath);
				Assert.fail();
			}
		} else {
			test.skip("Forgot Password not present");
			ExcelUtil.updateResult("TC_003", "Forgot Password link missing", "Skip", "", reportPath);
		}
	}

	@Test(priority = 4, dependsOnMethods = { "testForgotPasswordFlow" })
	public void testInstagramRedirect() throws IOException {
		driver.get(baseUrl);
		LoginPage lp = new LoginPage(driver);
		test = extent.createTest("Test Case 4: Instagram Redirect");

		if (!lp.isForgotPasswordVisible()) {
			lp.clickInstagramIcon();
			String currentUrl = driver.getCurrentUrl();

			if (currentUrl.contains("instagram.com")) {
				test.pass("User redirected to Instagram");
				ExcelUtil.updateResult("TC_004", "Redirection successful", "Pass", "", reportPath);
			} else {
				String screenshot = ScreenshotUtil.capture(driver, "InstagramRedirectFail");
				test.fail("Redirection failed. URL: " + currentUrl);
				ExcelUtil.updateResult("TC_004", "Redirect failed", "Fail", screenshot, reportPath);
				Assert.fail();
			}
		} else {
			test.skip("Instagram redirect skipped as Forgot Password is present");
			ExcelUtil.updateResult("TC_004", "Test skipped due to Forgot Password link", "Skip", "", reportPath);
		}
	}
}
