package com.nopcommerce.account;

import org.testng.annotations.Test;

import commons.BasePage;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_02_BasePage_1_Init {
	private WebDriver driver;
	private String projectPath;
	private String osName;
	private BasePage basePage;

	@BeforeClass
	public void beforeClass() {
		projectPath = System.getProperty("user.dir");
		osName = System.getProperty("os.name");
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriversW/chromedriver.exe");
		}
		driver = new ChromeDriver();
		basePage = new BasePage();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	//@Test
	public void Register_01_Empty_Data() {
		basePage.openPageURL(driver, "https://demo.nopcommerce.com/");

		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='FirstName-error']"),
				"First name is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"),
				"Password is required.");
	}

	//@Test
	public void Register_02_Invalid_Email() {
		basePage.openPageURL(driver, "https://demo.nopcommerce.com/");

		basePage.clickToElement(driver, "//a[@class='ico-register']");
		
		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Felix");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Nguyen");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", "felixnguyen");
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "1234BBaa");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "1234BBaa");
		
		basePage.clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	}

	//@Test
	public void Register_03_Invalid_Password() {
		basePage.openPageURL(driver, "https://demo.nopcommerce.com/");

		basePage.clickToElement(driver, "//a[@class='ico-register']");
		
		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Felix");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Nguyen");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", "felixnguyen@hotmail.com");
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "1234");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "1234");
		
		basePage.clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	//@Test
	public void Register_04_Invalid_Confirm_Password() {
		basePage.openPageURL(driver, "https://demo.nopcommerce.com/");

		basePage.clickToElement(driver, "//a[@class='ico-register']");
		
		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Felix");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Nguyen");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", "felixnguyen@hotmail.com");
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "1234bbaa");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "1234bb");
		
		basePage.clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
	}

	@Test
	public void Register_05_Success() {
		basePage.openPageURL(driver, "https://demo.nopcommerce.com/");

		basePage.clickToElement(driver, "//a[@class='ico-register']");
		
		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Felix");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Nguyen");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", getEmailRandom());
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "1234bbaa");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "1234bbaa");
		
		basePage.clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");
	}

	public String getEmailRandom() {
		Random rand = new Random();
		return "FelixNguyen" + rand.nextInt(9999) + "@hotmail.com";
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
