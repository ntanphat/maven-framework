package com.nopcommerce.account;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BasePage;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;
import pageObjects.user.HomePageObject;
import reportConfig.ExtentTestManager;
import pageObjects.user.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

@Epic("Demo Allure Report")
@Feature("Nopcommerce")
public class Level_19_Allure_Report extends BaseTest {
	private WebDriver driver;
	//private String browserName;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private String emailAddress = getEmailRandom();
	private String userURL = GlobalConstants.DEV_USER_URL;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, userURL);
		homePage = PageGeneratorManager.getHomePage(driver);
		registerPage = PageGeneratorManager.getRegisterPage(driver);
	}

	@Description("User 1: Register")
	@Story("Register to system")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void User_01_Register_Validate() {
		Assert.assertFalse(homePage.isRegisterLinkDisplayed());
		
		registerPage = homePage.clickToRegisterLink();
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		
		Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
		
	}

	@Description("User 2: Register Success")
	@Story("Register to system 2")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void User_02_Register_Register_Success() {
		registerPage.refreshCurrentPage(driver);
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
	
	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
