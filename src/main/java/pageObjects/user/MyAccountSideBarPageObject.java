package pageObjects.user;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import commons.BaseElement;
import commons.PageGeneratorManager;
import pageUIs.user.MyAccountSideBarPageUI;

public class MyAccountSideBarPageObject extends BaseElement {
	WebDriver driver;

	public MyAccountSideBarPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public MyAccountSideBarPageObject(WebDriver driver, long timeout) {
		super(driver);
		this.driver = driver;
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
	}
	
	public AddressPageObject openAddressPage() {
		waitForElementClickable(driver, MyAccountSideBarPageUI.ADDRESS_LINK_TEXT);
		clickToElement(driver, MyAccountSideBarPageUI.ADDRESS_LINK_TEXT);
		return PageGeneratorManager.getAddressPage(driver);
	}
	
	public OrdersPageObject openOrdersPage() {
		waitForElementClickable(driver, MyAccountSideBarPageUI.ORDERS_LINK_TEXT);
		clickToElement(driver, MyAccountSideBarPageUI.ORDERS_LINK_TEXT);
		return PageGeneratorManager.getOrdersPage(driver);
	}
	
	public RewardPointsPageObject openRewardPointsPage() {
		waitForElementClickable(driver, MyAccountSideBarPageUI.REWARD_POINTS_LINK_TEXT);
		clickToElement(driver, MyAccountSideBarPageUI.REWARD_POINTS_LINK_TEXT);
		return PageGeneratorManager.getRewardPointsPage(driver);
	}
	
	public CustomerPageObject openCustomerPage() {
		waitForElementClickable(driver, MyAccountSideBarPageUI.CUSTOMER_INFO_LINK_TEXT);
		clickToElement(driver, MyAccountSideBarPageUI.CUSTOMER_INFO_LINK_TEXT);
		return PageGeneratorManager.getCustomerPage(driver);
	}
	
	public MyAccountSideBarPageObject openDynamicSideBar (String pageName) {
		waitForElementClickable(driver, MyAccountSideBarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT,pageName);
		clickToElement(driver, MyAccountSideBarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT,pageName);
		switch (pageName) {
		case "Addresses":
			return PageGeneratorManager.getAddressPage(driver);
		case "Customer info":
			return PageGeneratorManager.getCustomerPage(driver);
		case "Reward points":
			return PageGeneratorManager.getRewardPointsPage(driver);
		case "Orders":
			return PageGeneratorManager.getOrdersPage(driver);
		default:
			new RuntimeException("Sidebar page name is incorrect.");
			return null;
		}
	}
	
	public void openDynamicSideBarByName (String pageName) {
		waitForElementClickable(driver, MyAccountSideBarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT,pageName);
		clickToElement(driver, MyAccountSideBarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT,pageName);
	}

}
