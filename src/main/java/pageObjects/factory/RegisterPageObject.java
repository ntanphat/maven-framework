package pageObjects.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class RegisterPageObject extends BasePageFactory{
	WebDriver driver;
	
	@FindBy(xpath="//input[@id='FirstName']")
	private WebElement firstNameTextbox;
	
	@FindBy(xpath="//input[@id='LastName']")
	private WebElement lastNameTextbox;
	
	@FindBy(xpath="//input[@id='Email']")
	private WebElement emailTextbox;
	
	@FindBy(xpath="//input[@id='Password']")
	private WebElement passwordTextbox;
	
	@FindBy(xpath="//input[@id='ConfirmPassword']")
	private WebElement confirmPasswordTextbox;
	
	@FindBy(xpath="//button[@id='register-button']")
	private WebElement registerButton;
	
	@FindBy(xpath="//span[@id='FirstName-error']")
	private WebElement firstNameErrorMsg;
	
	@FindBy(xpath="//span[@id='LastName-error']")
	private WebElement lastNameErrorMsg;
	
	@FindBy(xpath="//span[@id='Email-error']")
	private WebElement emailErrorMsg;
	
	@FindBy(xpath="//span[@id='Password-error']")
	private WebElement passwordErrorMsg;
	
	@FindBy(xpath="//span[@id='ConfirmPassword-error']")
	private WebElement confirmPasswordErrorMsg;
	
	@FindBy(xpath="//div[@class='result']")
	private WebElement registrationCompletedMsg;
	
	@FindBy(xpath="//div[@class='header-logo']//img")
	private WebElement nopCommerceLogo;
	

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, registerButton);
		clickToElement(driver, registerButton);
	}

	public String getFirstNameErrorMessage() {
		waitForElementVisible(driver, firstNameErrorMsg);
		return getElementText(driver, firstNameErrorMsg);
	}

	public String getEmailErrorMessage() {
		waitForElementVisible(driver, emailErrorMsg);
		return getElementText(driver, emailErrorMsg);
	}

	public String getLastNameErrorMessage() {
		waitForElementVisible(driver, lastNameErrorMsg);
		return getElementText(driver, lastNameErrorMsg);
	}

	public String getPasswordErrorMessage() {
		waitForElementVisible(driver, passwordErrorMsg);
		return getElementText(driver, passwordErrorMsg);
	}

	public String getConfirmPasswodErrorMessage() {
		waitForElementVisible(driver, confirmPasswordErrorMsg);
		return getElementText(driver, confirmPasswordErrorMsg);
	}

	public void clickToNopCommerceLogo() {
		waitForElementClickable(driver, nopCommerceLogo);
		clickToElement(driver, nopCommerceLogo);
		
	}

	public void enterToFirstNameTextbox(String firstNameValue) {
		waitForElementVisible(driver, firstNameTextbox);
		sendKeyToElement(driver, firstNameTextbox, firstNameValue);
	}

	public void enterToLastNameTextbox(String lastNameValue) {
		waitForElementVisible(driver, lastNameTextbox);
		sendKeyToElement(driver, lastNameTextbox, lastNameValue);
	}

	public void enterToEmailTextbox(String emailValue) {
		waitForElementVisible(driver, emailTextbox);
		sendKeyToElement(driver, emailTextbox, emailValue);
	}

	public void enterToPasswordTextbox(String passwordValue) {
		waitForElementVisible(driver, passwordTextbox);
		sendKeyToElement(driver, passwordTextbox, passwordValue);
	}

	public void enterToConfirmPasswordTextbox(String confirmPasswordValue) {
		waitForElementVisible(driver, confirmPasswordTextbox);
		sendKeyToElement(driver, confirmPasswordTextbox, confirmPasswordValue);
	}

	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, registrationCompletedMsg);
		return getElementText(driver, registrationCompletedMsg);
	}
}
