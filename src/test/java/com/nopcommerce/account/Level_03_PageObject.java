package com.nopcommerce.account;

import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.user.CustomerPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.RegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_PageObject extends BasePage {
	private WebDriver driver;
	private String projectPath;
	private String osName;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private CustomerPageObject customerPage;
	private String emailAddress = getEmailRandom();

	@BeforeClass
	public void beforeClass() {
		projectPath = System.getProperty("user.dir");
		osName = System.getProperty("os.name");
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
		} else {
			System.setProperty("webdriver.edge.driver", projectPath + "/browserDriversW/msedgedriver.exe");
		}
		driver = new EdgeDriver();
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		driver.get("https://demo.nopcommerce.com/");
		homePage = new HomePageObject(driver);
	}

	@Test
	public void User_01_Register_Empty_Data() {
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
		Assert.assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");
		Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password is required.");
		Assert.assertEquals(registerPage.getConfirmPasswodErrorMessage(), "Password is required.");
	}

	@Test
	public void User_02_Register_Invalid_Email() {
		registerPage.clickToNopCommerceLogo();
		homePage = new HomePageObject(driver);
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
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
		registerPage.clickToNopCommerceLogo();
		homePage = new HomePageObject(driver);
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.enterToFirstNameTextbox("Felix");
		registerPage.enterToLastNameTextbox("Nguyen");
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox("1234");
		registerPage.enterToConfirmPasswordTextbox("1234");
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void User_04_Register_Invalid_Confirm_Password() {
		registerPage.clickToNopCommerceLogo();
		homePage = new HomePageObject(driver);
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.enterToFirstNameTextbox("Felix");
		registerPage.enterToLastNameTextbox("Nguyen");
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox("1234bbaa");
		registerPage.enterToConfirmPasswordTextbox("1234bb");
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getConfirmPasswodErrorMessage(), "The password and confirmation password do not match.");
	}

	@Test
	public void User_05_Register_Success() {
		registerPage.clickToNopCommerceLogo();
		homePage = new HomePageObject(driver);
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
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
		registerPage.clickToNopCommerceLogo();
		homePage = new HomePageObject(driver);
		homePage.clickLoginLink();
		loginPage = new UserLoginPageObject(driver);
		loginPage.enterToEmailTextbox(emailAddress);
		loginPage.enterToPasswordTextBox("1234bbaa");
		loginPage.clickToLoginButton();
		homePage = new HomePageObject(driver);
		homePage.clickToMyAccountLink();
		customerPage = new CustomerPageObject(driver);
		Assert.assertEquals(customerPage.getFirstNameAttributeValue(), "Felix");
		Assert.assertEquals(customerPage.getLastNameAttributeValue(), "Nguyen");
		Assert.assertEquals(customerPage.getEmailAttributeValue(), emailAddress);
		
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
