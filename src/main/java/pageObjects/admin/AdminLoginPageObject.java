package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage{
	WebDriver driver;

	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToEmailTextBox(String emailAddress) {
		waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void enterToPasswordTextBox(String password) {
		waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, password);
		
	}

	public AdminDashboardPageObject clickLoginButon() {
		waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getAdminDashboardPage(driver);
		
	}
	
	public AdminDashboardPageObject loginToAdmin(String emailAddress, String password) {
		enterToEmailTextBox(emailAddress);
		enterToPasswordTextBox(password);
		return clickLoginButon();
	}
	
}
