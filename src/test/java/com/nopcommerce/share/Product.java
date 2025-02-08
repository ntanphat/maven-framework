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

public class Product extends BaseTest {
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
	
		System.out.println("Email at Product: " + Common_Register.emailAddress);
		System.out.println("Password at Product: " + Common_Register.password);
	}


	@Test
	public void Product_01_New_Product() {

	}
	
	@Test
	public void Product_01_View_Product() {

	}
	
	@Test
	public void Product_01_Edit_Product() {

	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
