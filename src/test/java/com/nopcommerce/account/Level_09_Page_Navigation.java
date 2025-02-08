package com.nopcommerce.account;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
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

public class Level_09_Page_Navigation extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private CustomerPageObject customerPage;
	private AddressPageObject addressPage;
	private OrdersPageObject orderPage;
	private RewardPointsPageObject rewardPointPage;
	private String emailAddress = getEmailRandom();

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, "");

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
		addressPage = customerPage.openAddressPage();
		
		//Address Page -> Customer Page
		customerPage = addressPage.openCustomerPage();
		
		//Customer Page -> Order Page
		orderPage = customerPage.openOrdersPage();
		
		//Order Page -> Address Page
		addressPage = orderPage.openAddressPage();
		
		//Address Page -> Reward Point Page
		rewardPointPage = addressPage.openRewardPointsPage();
		
		//Reward Page -> Customer Page
		customerPage = orderPage.openCustomerPage();
		
		//Customer Page -> Reward Piont Page
		rewardPointPage = customerPage.openRewardPointsPage();
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
