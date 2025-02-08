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

public class Level_17_ReoprtNG extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private String emailAddress = getEmailRandom();
	private String userURL = GlobalConstants.DEV_USER_URL;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, userURL);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void User_01_Register_Validate() {
		log.info("Register - Step 01: Verify register link is displayed");
		verifyFalse(homePage.isRegisterLinkDisplayed());
		
		log.info("Register - Step 02: Click to register link");
		registerPage = homePage.clickToRegisterLink();
		
		log.info("Register - Step 03: Click to register button");
		registerPage.clickToRegisterButton();
		
		log.info("Register - Step 04: Verify error msg at First Name text box");
		verifyEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		
		log.info("Register - Step 05: Verify error msg at Last Name text box");
		verifyEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
		
	}

	@Test
	public void User_02_Register_Register_Success() {
		registerPage.refreshCurrentPage(driver);
		log.info("Register - Step 06: Enter to First Name text box");
		registerPage.enterToFirstNameTextbox("Felix");
		
		log.info("Register - Step 07: Enter to Last Name text box");
		registerPage.enterToLastNameTextbox("Nguyen");
		
		log.info("Register - Step 08: Enter to Email text box");
		registerPage.enterToEmailTextbox(emailAddress);
		
		log.info("Register - Step 09: Enter to Password text box");
		registerPage.enterToPasswordTextbox("1234bbaa");
		
		log.info("Register - Step 10: Enter to Confirm Password text box");
		registerPage.enterToConfirmPasswordTextbox("1234bbaa");
		
		log.info("Register - Step 11: Click to Register button");
		registerPage.clickToRegisterButton();
		
		log.info("Register - Step 12: Verify Success message is display");
		registerPage.getRegisterSuccessMessage();
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed.");
	}
	
	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
