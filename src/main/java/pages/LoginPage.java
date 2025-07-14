package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	private WebDriver driver;
	private WebDriverWait wait;

	// Locators
	private By usernameField = By.name("username");
	private By passwordField = By.name("password");
	private By loginButton = By.xpath("//button[contains(text(),'Login')]");
	private By forgotPasswordLink = By.linkText("Forgot password?");
	private By instagramIcon = By.cssSelector("a[href*='instagram']");
	private By errorToast = By.xpath("//*[contains(text(),'Invalid username or password')]");
	private By userNotExist = By.xpath("//*[contains(text(),'Username does not exist')]");

	// Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	// Actions
	public void enterUsername(String username) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
	}

	public void enterPassword(String password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
	}

	public void clickLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
	}

	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}

	public boolean isForgotPasswordVisible() {
		return driver.findElements(forgotPasswordLink).size() > 0;
	}

	public void clickForgotPassword() {
		if (isForgotPasswordVisible()) {
			driver.findElement(forgotPasswordLink).click();
		}
	}

	public void sendForgotUsername(String username) {
		enterUsername(username);
		driver.findElement(By.xpath("//button[contains(text(),'Send')]")).click();
	}

	public boolean isErrorToastVisible() {
		return driver.findElements(errorToast).size() > 0;
	}

	public boolean isUserNotExistVisible() {
		return driver.findElements(userNotExist).size() > 0;
	}

	public void clickInstagramIcon() {
		wait.until(ExpectedConditions.elementToBeClickable(instagramIcon)).click();
	}

	public boolean waitForLoginElements() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
			wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
			wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
