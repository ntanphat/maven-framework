package com.nopcommerce.account;//package com.nopcommerce.account;
//
//import org.testng.annotations.Test;
//
//import com.relevantcodes.extentreports.LogStatus;
//
//import commons.BasePage;
//import commons.BaseTest;
//import commons.GlobalConstants;
//import commons.PageGeneratorManager;
//import pageObjects.user.AddressPageObject;
//import pageObjects.user.CustomerPageObject;
//import pageObjects.user.HomePageObject;
//import pageObjects.user.UserLoginPageObject;
//import reportConfig.ExtentManager;
//import pageObjects.user.OrdersPageObject;
//import pageObjects.user.RegisterPageObject;
//import pageObjects.user.RewardPointsPageObject;
//
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Parameters;
//
//import java.lang.reflect.Method;
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//
//public class Level_18_Extent_V2 extends BaseTest {
//	private WebDriver driver;
//	private HomePageObject homePage;
//	private RegisterPageObject registerPage;
//	private String emailAddress = getEmailRandom();
//	private String userURL = GlobalConstants.DEV_USER_URL;
//
//	@Parameters({ "browser" })
//	@BeforeClass
//	public void beforeClass(String browserName) {
//		driver = getBrowserDriver(browserName, userURL);
//		homePage = PageGeneratorManager.getHomePage(driver);
//		registerPage = PageGeneratorManager.getRegisterPage(driver);
//	}
//
//	@Test
//	public void User_01_Register_Validate(Method method) {
//		ExtentManager.startTest(method.getName(), "User_01_Register_Validate");
//		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 01: Verify register link is displayed");
//		Assert.assertFalse(homePage.isRegisterLinkDisplayed());
//		
//		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 02: Click to register link");
//		registerPage = homePage.clickToRegisterLink();
//		
//		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 03: Click to register button");
//		registerPage.clickToRegisterButton();
//		
//		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 04: Verify error msg at First Name text box");
//		Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
//		
//		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 05: Verify error msg at Last Name text box");
//		Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
//		
//	}
//
//	@Test
//	public void User_02_Register_Register_Success(Method method) {
//		ExtentManager.startTest(method.getName(), "User_02_Register_Register_Success");
//		registerPage.refreshCurrentPage(driver);
//		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 06: Enter to First Name text box");
//		registerPage.enterToFirstNameTextbox("Felix");
//		
//		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 07: Enter to Last Name text box");
//		registerPage.enterToLastNameTextbox("Nguyen");
//		
//		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 08: Enter to Email text box");
//		registerPage.enterToEmailTextbox(emailAddress);
//		
//		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 09: Enter to Password text box");
//		registerPage.enterToPasswordTextbox("1234bbaa");
//		
//		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 10: Enter to Confirm Password text box");
//		registerPage.enterToConfirmPasswordTextbox("1234bbaa");
//		
//		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 11: Click to Register button");
//		registerPage.clickToRegisterButton();
//		
//		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 12: Verify Success message is display");
//		registerPage.getRegisterSuccessMessage();
//		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
//	}
//	
//	@AfterClass
//	public void afterClass() {
//		closeBrowser();
//	}
//
//}
