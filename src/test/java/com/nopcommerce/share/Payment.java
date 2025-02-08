package com.nopcommerce.share;

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

public class Payment extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private UserLoginPageObject loginPage;
	private CustomerPageObject customerPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		homePage = PageGeneratorManager.getHomePage(driver);
		
		loginPage = homePage.clickLoginLink();
		loginPage.enterToEmailTextbox(Common_Register.emailAddress);
		loginPage.enterToPasswordTextBox(Common_Register.password);
		homePage = loginPage.clickToLoginButton();
		customerPage = homePage.clickToMyAccountLink();
		Assert.assertEquals(customerPage.getFirstNameAttributeValue(), Common_Register.firstName);
		Assert.assertEquals(customerPage.getLastNameAttributeValue(), Common_Register.lastName);
		Assert.assertEquals(customerPage.getEmailAttributeValue(), Common_Register.emailAddress);
	
		System.out.println("Email at Payment: " + Common_Register.emailAddress);
		System.out.println("Password at Payment: " + Common_Register.password);
	}


	@Test
	public void Payment_01_Visa() {

	}
	
	@Test
	public void Payment_02_Cheque() {

	}
	
	@Test
	public void Payment_03_Paypal() {

	}
	
	@Test
	public void Payment_04_Bitcoin() {

	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
