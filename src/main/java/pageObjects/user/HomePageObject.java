package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BaseElement;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.user.HomePageUI;

public class HomePageObject extends BaseElement {
	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Click to Register Link")
	public RegisterPageObject clickToRegisterLink() {
		waitForElementVisible(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}

	public UserLoginPageObject clickLoginLink() {
		waitForElementVisible(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);
	}

	public CustomerPageObject clickToMyAccountLink() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getCustomerPage(driver);
	}
	
	public CustomerPageObject openMyAccountLink() {
		String url = getElementAttributeValue(driver, HomePageUI.MY_ACCOUNT_LINK, "href");
		openPageURL(driver, url);
		return PageGeneratorManager.getCustomerPage(driver);
	}

	public void clickLogoutLink() {
		waitForElementVisible(driver, HomePageUI.LOGOUT_LINK);
		clickToElement(driver, HomePageUI.LOGOUT_LINK);
		
	}

	@Step("Verify Register Link is displayed")
	public boolean isRegisterLinkDisplayed() {
		waitForElementVisible(driver, HomePageUI.REGISTER_LINK);
		return isElementDisplayed(driver, HomePageUI.REGISTER_LINK);
	}

	

}
