package com.nopcommerce.account;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.CustomerPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_22_Close_Browser extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		homePage = PageGeneratorManager.getHomePage(driver);
		
		homePage.clickToHeaderLinkByName("Register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);
//		registerPage = PageGeneratorManager.getRegisterPage(driver);
		
		registerPage.clickToButtonByText("Register");
		
		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("FirstName"), "First name is required...");
		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("LastName"), "Last name is required.");
		
	}

	@Test
	public void User_01_Register_Empty_Data() {
		
	}

	@Test
	public void User_02_Register_Invalid_Email() {
		
	}

	@Test
	public void User_03_Register_Invalid_Password() {
	
	}

	@Test
	public void User_04_Register_Invalid_Confirm_Password() {
	
	}

	@Test
	public void User_05_Register_Success() {
	
	}

	@Test
	public void User_06_Login_Success() {
	
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowser();
	}

}
