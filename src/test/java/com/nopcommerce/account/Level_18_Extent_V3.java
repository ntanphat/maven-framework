package com.nopcommerce.account;//package com.nopcommerce.account;
//
//import org.testng.annotations.Test;
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
//public class Level_18_Extent_V3 extends BaseTest {
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
//		Assert.assertFalse(homePage.isRegisterLinkDisplayed());
//		
//		registerPage = homePage.clickToRegisterLink();
//		
//		registerPage.clickToRegisterButton();
//		
//		Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
//		
//		Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
//		
//	}
//
//	@Test
//	public void User_02_Register_Register_Success(Method method) {
//		registerPage.refreshCurrentPage(driver);
//		registerPage = homePage.clickToRegisterLink();
//		
//		registerPage.enterToFirstNameTextbox("Felix");
//		
//		registerPage.enterToLastNameTextbox("Nguyen");
//		
//		registerPage.enterToEmailTextbox(emailAddress);
//		
//		registerPage.enterToPasswordTextbox("1234bbaa");
//		
//		registerPage.enterToConfirmPasswordTextbox("1234bbaa");
//		
//		registerPage.clickToRegisterButton();
//		
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
