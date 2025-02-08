package com.nopcommerce.account;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.user.AddressPageObject;
import pageObjects.user.CustomerPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.OrdersPageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.RewardPointsPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_12_Dynamic_Locator_Rest_Param extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private CustomerPageObject customerPage;
	private AddressPageObject addressPage;
	private OrdersPageObject orderPage;
	private RewardPointsPageObject rewardPointPage;
	private String emailAddress = getEmailRandom();
	private String adminURL = GlobalConstants.DEV_ADMIN_URL;
	private String userURL = GlobalConstants.DEV_USER_URL;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, userURL);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void User_01_Register_Success() {
		//homePage = registerPage.clickToNopCommerceLogo();
		registerPage = homePage.clickToRegisterLink();
		registerPage.enterToFirstNameTextbox("Felix");
		registerPage.enterToLastNameTextbox("Nguyen");
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox("1234bbaa");
		registerPage.enterToConfirmPasswordTextbox("1234bbaa");
		registerPage.clickToRegisterButton();
		registerPage.getRegisterSuccessMessage();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}

	@Test
	public void User_02_Login_Success() {
		homePage = registerPage.clickToNopCommerceLogo();
		loginPage = homePage.clickLoginLink();
		loginPage.enterToEmailTextbox(emailAddress);
		loginPage.enterToPasswordTextBox("1234bbaa");
		homePage = loginPage.clickToLoginButton();
		customerPage = homePage.clickToMyAccountLink();
		Assert.assertEquals(customerPage.getFirstNameAttributeValue(), "Felix");
		Assert.assertEquals(customerPage.getLastNameAttributeValue(), "Nguyen");
		Assert.assertEquals(customerPage.getEmailAttributeValue(), emailAddress);
	}
	
	@Test
	public void User_03_Switch_Page() {
		//Customer Page -> Address Page
		addressPage = (AddressPageObject) customerPage.openDynamicSideBar("Addresses");
		
		//Address Page -> Customer Page
		customerPage = (CustomerPageObject) addressPage.openDynamicSideBar("Customer info");
		
		//Customer Page -> Order Page
		orderPage = (OrdersPageObject) customerPage.openDynamicSideBar("Orders");
		
		//Order Page -> Address Page
		addressPage = (AddressPageObject) orderPage.openDynamicSideBar("Addresses");
		
		//Address Page -> Reward Point Page
		rewardPointPage = (RewardPointsPageObject) addressPage.openDynamicSideBar("Reward points");
		
		//Reward Page -> Customer Page
		customerPage = (CustomerPageObject) rewardPointPage.openDynamicSideBar("Customer info");
		
		//Customer Page -> Reward Piont Page
		rewardPointPage = (RewardPointsPageObject) customerPage.openDynamicSideBar("Reward points");
	}
	
	@Test
	public void User_04_Switch_Page() {
		//Reward Page -> Customer Page
		orderPage.openDynamicSideBarByName("Customer info");
		customerPage = PageGeneratorManager.getCustomerPage(driver);
		
		//Customer Page -> Reward Piont Page
		customerPage.openDynamicSideBarByName("Reward points");
		rewardPointPage = PageGeneratorManager.getRewardPointsPage(driver);
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
