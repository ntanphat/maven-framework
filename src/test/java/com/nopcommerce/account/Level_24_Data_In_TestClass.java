package com.nopcommerce.account;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.CustomerPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.UserLoginPageObject;
import reportConfig.ExtentTestManager;
import ultilities.FakerConfig;

import java.lang.reflect.Method;

public class Level_24_Data_In_TestClass extends BaseTest {

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		this.browserName = browserName.toUpperCase();
		driver = getBrowserDriver(browserName, userURL);
		homePage = PageGeneratorManager.getHomePage(driver);
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		firstName = "Felix";
		lastName = "Nguyen";
		emailAddress = getEmailRandom();
		password = "1234aaAA";
	}

	@Test
	public void User_01_Register_Validate(Method method) {
		ExtentTestManager.startTest(method.getName() + " - Run on " + browserName, "User_01_Register_Validate");
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Verify register link is displayed");
		Assert.assertFalse(homePage.isRegisterLinkDisplayed());
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Click to register link");
		registerPage = homePage.clickToRegisterLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Click to register button");
		registerPage.clickToRegisterButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04: Verify error msg at First Name text box");
		Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05: Verify error msg at Last Name text box");
		Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
		
	}

	@Test
	public void User_02_Register_Register_Success(Method method) {
		ExtentTestManager.startTest(method.getName() + " - Run on " + browserName, "User_02_Register_Register_Success");
		registerPage.refreshCurrentPage(driver);
		registerPage = homePage.clickToRegisterLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06: Enter to First Name text box");
		registerPage.enterToFirstNameTextbox(firstName);
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07: Enter to Last Name text box");
		registerPage.enterToLastNameTextbox(lastName);
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 08: Enter to Email text box");
		registerPage.enterToEmailTextbox(emailAddress);
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 09: Enter to Password text box");
		registerPage.enterToPasswordTextbox(password);
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 10: Enter to Confirm Password text box");
		registerPage.enterToConfirmPasswordTextbox(password);
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 11: Click to Register button");
		registerPage.clickToRegisterButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 12: Verify Success message is display");
		registerPage.getRegisterSuccessMessage();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}

	@Test
	public void User_06_Login_Success() {
		homePage = registerPage.clickToNopCommerceLogo();

		homePage.clickToHeaderLinkByName("Log in");
		loginPage = PageGeneratorManager.getUserLoginPage(driver);

		loginPage.enterToTextboxByID("Email",emailAddress);
		loginPage.enterToTextboxByID("Password",password);

		loginPage.clickToButtonByText("Log in");
		homePage = PageGeneratorManager.getHomePage(driver);

		homePage.clickToHeaderLinkByName("My account");
		customerPage = PageGeneratorManager.getCustomerPage(driver);

		Assert.assertEquals(customerPage.getTextboxAttributeValueByID("FirstName"), firstName);
		Assert.assertEquals(customerPage.getTextboxAttributeValueByID("LastName"), lastName);
		Assert.assertEquals(customerPage.getTextboxAttributeValueByID("Email"), emailAddress);
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

	private WebDriver driver;
	private String browserName;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	UserLoginPageObject loginPage;
	CustomerPageObject customerPage;
	private String emailAddress, firstName, lastName, password;
	private String userURL = GlobalConstants.DEV_USER_URL;
}
