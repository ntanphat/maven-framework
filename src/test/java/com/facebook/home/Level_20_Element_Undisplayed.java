package com.facebook.home;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.facebook.HomePageObject;
import pageObjects.facebook.PageGeneratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Level_20_Element_Undisplayed extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;

	@Parameters({ "browser","url" })
	@BeforeClass
	public void beforeClass(String browserName, String URL) {
		driver = getBrowserDriver(browserName,URL);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Home_01_Element_Displayed() {
		homePage.clickToCreateNewAccountButton();
		verifyTrue(homePage.isFirstNameTextboxDisplayed());
		verifyTrue(homePage.isSurnameTextboxDisplayed());
		verifyTrue(homePage.isEmailTextboxDisplayed());
		verifyTrue(homePage.isPasswordTextboxDisplayed());
		
		homePage.enterToEmailTextbox("ntphat@gmail.com");
		log.info("Verify Confirm Email text box is displayed");
		verifyTrue(homePage.isConfirmEmailTextboxDisplayed());
	}
	
	@Test
	public void Home_02_Element_Undisplayed_In_HTML() {
		//After doing clear email text box, confirm email is not displayed but stil exist in DOM
		homePage.enterToEmailTextbox("");
		homePage.sleepInSec(2);
		log.info("Verify Confirm Email text box is not displayed");
		verifyFalse(homePage.isConfirmEmailTextboxDisplayed());
	}
	
	
	public void Home_03_Element_Undisplayed_Not_In_HTML() {
		homePage.clickToCloseSignUpButton();
		
		log.info("Verify First Name text box is not displayed"); //30s
		verifyFalse(homePage.isFirstNameTextboxDisplayed());
		
		log.info("Verify Surname text box is not displayed");//30s
		verifyFalse(homePage.isSurnameTextboxDisplayed());
		
		log.info("Verify Email text box is not displayed");//30s
		verifyFalse(homePage.isEmailTextboxDisplayed());
		
		log.info("Verify Password text box is not displayed");//30s
		verifyFalse(homePage.isPasswordTextboxDisplayed());
	}
	
	@Test
	public void Home_03_Element_Undisplayed_Not_In_HTML_02() {
		homePage.clickToCloseSignUpButton();
		
		log.info("Verify First Name text box is not displayed");
		verifyTrue(homePage.isFirstNameTextboxUnDisplayed());
		
		log.info("Verify Surname text box is not displayed");
		verifyTrue(homePage.isSurnameTextboxUnDisplayed());
		
		log.info("Verify Email text box is not displayed");
		verifyTrue(homePage.isEmailTextboxUnDisplayed());
		
		log.info("Verify Password text box is not displayed");
		verifyTrue(homePage.isPasswordTextboxUnDisplayed());
	}


	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
