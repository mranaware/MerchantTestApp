package mobile;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;

public class MobileBaseTest {
	protected AndroidDriver driver;
	protected static ExtentReports extent;
	protected ExtentTest test;
	protected static String reportPath;

	@SuppressWarnings("deprecation")

	@BeforeMethod
	public void setupAppium() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformName", "ANDROID");
		capabilities.setCapability("automationName", "UIAutomator2");
		String appPath = null;
		capabilities.setCapability("app", appPath);
		capabilities.setCapability("appWaitActivity", "*");

		// ✅ Add timeout increase here
		capabilities.setCapability("uiautomator2ServerLaunchTimeout", 60000); // 60 seconds

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null)
			driver.quit();
		if (extent != null)
			extent.flush();
	}
}
