package com.nopcommerce.account;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.CustomerPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_07_Page_Manager_3 extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private CustomerPageObject customerPage;
	private String emailAddress = getEmailRandom();

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, "");

		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void User_01_Register_Empty_Data() {
		registerPage = homePage.clickToRegisterLink();
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
		Assert.assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");
		Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password is required.");
		Assert.assertEquals(registerPage.getConfirmPasswodErrorMessage(), "Password is required.");
	}

	@Test
	public void User_02_Register_Invalid_Email() {
		homePage = registerPage.clickToNopCommerceLogo();
		registerPage = homePage.clickToRegisterLink();
		registerPage.enterToFirstNameTextbox("Felix");
		registerPage.enterToLastNameTextbox("Nguyen");
		registerPage.enterToEmailTextbox("felixnguyen");
		registerPage.enterToPasswordTextbox("1234BBaa");
		registerPage.enterToConfirmPasswordTextbox("1234BBaa");
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getEmailErrorMessage(), "Wrong email");

	}

	@Test
	public void User_03_Register_Invalid_Password() {
		homePage = registerPage.clickToNopCommerceLogo();
		registerPage = homePage.clickToRegisterLink();
		registerPage.enterToFirstNameTextbox("Felix");
		registerPage.enterToLastNameTextbox("Nguyen");
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox("1234");
		registerPage.enterToConfirmPasswordTextbox("1234");
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getPasswordErrorMessage(),
				"Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void User_04_Register_Invalid_Confirm_Password() {
		homePage = registerPage.clickToNopCommerceLogo();
		registerPage = homePage.clickToRegisterLink();
		registerPage.enterToFirstNameTextbox("Felix");
		registerPage.enterToLastNameTextbox("Nguyen");
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox("1234bbaa");
		registerPage.enterToConfirmPasswordTextbox("1234bb");
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getConfirmPasswodErrorMessage(),
				"The password and confirmation password do not match.");
	}

	@Test
	public void User_05_Register_Success() {
		homePage = registerPage.clickToNopCommerceLogo();
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
	public void User_06_Login_Success() {
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

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
