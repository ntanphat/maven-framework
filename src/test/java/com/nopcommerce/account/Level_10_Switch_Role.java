package com.nopcommerce.account;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.user.AddressPageObject;
import pageObjects.user.CustomerPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.OrdersPageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.RewardPointsPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_10_Switch_Role extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private UserLoginPageObject userLoginPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	private String emailAddress = getEmailRandom();
	private String adminURL, userURL;

	@Parameters({"browser","adminURL","userURL"})
	@BeforeClass
	public void beforeClass(String browserName, String adminURL, String userURL) {
		driver = getBrowserDriver(browserName,userURL);
		this.adminURL = adminURL;
		this.userURL = userURL;

		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void User_01_User_To_Admin() {
		registerPage = homePage.clickToRegisterLink();
		registerPage.enterToFirstNameTextbox("Felix");
		registerPage.enterToLastNameTextbox("Nguyen");
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox("1234bbaa");
		registerPage.enterToConfirmPasswordTextbox("1234bbaa");
		registerPage.clickToRegisterButton();
		registerPage.getRegisterSuccessMessage();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		homePage = registerPage.clickToNopCommerceLogo();
		userLoginPage = homePage.clickLoginLink();
		homePage = userLoginPage.loginToUser(emailAddress,"1234bbaa");
		//...
		//Logout
		homePage.clickLogoutLink();
		
		//Home Page (User) -> Login Page (Admin) 
		homePage.openPageURL(driver, this.adminURL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		adminDashboardPage = adminLoginPage.loginToAdmin("admin@yourstore.com", "com/nopcommerce/admin");
		Assert.assertTrue(adminDashboardPage.isPageLoadedSuccess(driver));
	}

	@Test
	public void User_02_Admin_To_User() {
		adminLoginPage = adminDashboardPage.clickLogoutLink();
		//Home Page (Admin) -> Home Page (User)
		adminLoginPage.openPageURL(driver, this.userURL);
		homePage = PageGeneratorManager.getHomePage(driver);
		
		userLoginPage = homePage.clickLoginLink();
		homePage = userLoginPage.loginToUser(emailAddress,"1234bbaa");
		
	}
	
	

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
