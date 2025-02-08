package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BaseElement;
import io.qameta.allure.Step;
import pageUIs.user.RegisterPageUI;

public class RegisterPageObject extends BaseElement{
	WebDriver driver;
	
	public RegisterPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@Step("Click to Register button")
	public void clickToRegisterButton() {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
		
	}
	public String getFirstNameErrorMessage() {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_ERROR_MSG);
		return getElementText(driver, RegisterPageUI.FIRSTNAME_ERROR_MSG);
	}
	
	public String getLastNameErrorMessage() {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_ERROR_MSG);
		return getElementText(driver, RegisterPageUI.LASTNAME_ERROR_MSG);
	}
	public String getEmailErrorMessage() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ERROR_MSG);
		return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MSG);
	}
	public String getPasswordErrorMessage() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_ERROR_MSG);
		return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MSG);
	}
	public String getConfirmPasswodErrorMessage() {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
		return getElementText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
	}
	
	
	@Step("Enter to First Name textbox with value: {0}")
	public void enterToFirstNameTextbox(String firstNameValue) {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.FIRSTNAME_TEXTBOX, firstNameValue);
		
	}
	
	@Step("Enter to Last Name textbox with value: {0}")
	public void enterToLastNameTextbox(String lastNameValue) {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.LASTNAME_TEXTBOX, lastNameValue);
		
	}
	
	@Step("Enter to Email textbox with value: {0}")
	public void enterToEmailTextbox(String emailValue) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailValue);
		
	}
	
	@Step("Enter to Password textbox with value: {0}")
	public void enterToPasswordTextbox(String passwordValue) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, passwordValue);
		
	}
	
	@Step("Enter to Confirm Password textbox with value: {0}")
	public void enterToConfirmPasswordTextbox(String passwordValue) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, passwordValue);
		
	}
	
	@Step("Get Register Success msg")
	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, RegisterPageUI.RESGISTRATION_COMPLETED_MSG);
		return getElementText(driver, RegisterPageUI.RESGISTRATION_COMPLETED_MSG);
	}

	

	
	
	
}
