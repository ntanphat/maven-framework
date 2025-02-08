package com.nopcommerce.cookie;

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

public class Order extends BaseTest {
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
		
		loginPage.setCookies(driver, Common_Register.cookies);
		loginPage.sleepInSec(5);
		loginPage.refreshCurrentPage(driver);
		
		customerPage = homePage.openMyAccountLink();
		Assert.assertEquals(customerPage.getFirstNameAttributeValue(), Common_Register.firstName);
		Assert.assertEquals(customerPage.getLastNameAttributeValue(), Common_Register.lastName);
		Assert.assertEquals(customerPage.getEmailAttributeValue(), Common_Register.emailAddress);
	}

	@Test
	public void Order_01_Invalid_Address() {

	}
	
	@Test
	public void Order_02_Invalid_SSN() {

	}
	
	@Test
	public void Order_03_Invalid_Phone() {

	}
	
	@Test
	public void Order_04_Invalid_Phone() {

	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
