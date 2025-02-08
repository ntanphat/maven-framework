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

public class Level_15_Assert_Verify extends BaseTest {
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
	public void User_01_Register_Success() {
		//Verify register link is displayed -> FAILED
		verifyFalse(homePage.isRegisterLinkDisplayed());
		registerPage = homePage.clickToRegisterLink();
		
		registerPage.clickToRegisterButton();
		//Verify error msg at First Name text box -> PASSED
		verifyEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		
		//Verify error msg at Last Name text box -> FAILED
		verifyEquals(registerPage.getLastNameErrorMessage(), "Last name is required");
		
		registerPage.enterToFirstNameTextbox("Felix");
		registerPage.enterToLastNameTextbox("Nguyen");
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox("1234bbaa");
		registerPage.enterToConfirmPasswordTextbox("1234bbaa");
		registerPage.clickToRegisterButton();
		registerPage.getRegisterSuccessMessage();
		
		//Verify success msg -> FAILED
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed.");
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
