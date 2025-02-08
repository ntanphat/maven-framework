package com.nopcommerce.cookie;


import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.HomePageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.UserLoginPageObject;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Common_Register extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private String password;
	public static String emailAddress, firstName, lastName;
	public static Set<Cookie> cookies;

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		
		emailAddress = getEmailRandom();
		password = "1234bbaa";
		firstName = "Felix";
		lastName = "Nguyen";
		
		registerPage = homePage.clickToRegisterLink();
		registerPage.enterToFirstNameTextbox(firstName);
		registerPage.enterToLastNameTextbox(lastName);
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		registerPage.getRegisterSuccessMessage();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		homePage = registerPage.clickToNopCommerceLogo();
		loginPage = homePage.clickLoginLink();
		loginPage.enterToEmailTextbox(emailAddress);
		loginPage.enterToPasswordTextBox(password);
		homePage = loginPage.clickToLoginButton();

		homePage.sleepInSec(5);
		cookies = homePage.getBrowserCookies(driver);
		closeBrowser();
	}


}
