package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.user.CustomerPageUI;

public class CustomerPageObject extends MyAccountSideBarPageObject{
	WebDriver driver;

	public CustomerPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public String getFirstNameAttributeValue() {
		waitForElementVisible(driver, CustomerPageUI.FIRSTNAME_TEXTBOX);
		return getElementAttributeValue(driver, CustomerPageUI.FIRSTNAME_TEXTBOX, "value");
	}

	public String getLastNameAttributeValue() {
		waitForElementVisible(driver, CustomerPageUI.LASTNAME_TEXTBOX);
		return getElementAttributeValue(driver, CustomerPageUI.LASTNAME_TEXTBOX, "value");
	}

	public String getEmailAttributeValue() {
		waitForElementVisible(driver, CustomerPageUI.EMAIL_TEXTBOX);
		return getElementAttributeValue(driver, CustomerPageUI.EMAIL_TEXTBOX, "value");
	}
}
