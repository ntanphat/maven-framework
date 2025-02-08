package com.jquery.table;

import org.testng.annotations.Test;
import commons.BaseTest;
import pageObjects.jquery.HomePageObject;
import pageObjects.jquery.PageGeneratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_13_Handle_DataTable extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	public void TC_01_Search() {
		//Search data on table
		homePage.inputToColumnTextboxByName("Females", "283821");
		homePage.sleepInSec(2);
		homePage.inputToColumnTextboxByName("Males", "756");
		homePage.sleepInSec(2);
		homePage.inputToColumnTextboxByName("Country", "Argentina");
		homePage.sleepInSec(2);
		homePage.inputToColumnTextboxByName("Total", "687522");
		homePage.sleepInSec(2);
	}

	public void TC_02_Paging() {
		homePage.clickToPageNumber("10");
		homePage.sleepInSec(2);
		Assert.assertTrue(homePage.isPageActiveByNumber("10"));
		
		homePage.clickToPageNumber("13");
		homePage.sleepInSec(2);
		Assert.assertTrue(homePage.isPageActiveByNumber("13"));
	}
	
	public void TC_03_Verify_Data() {
		Assert.assertTrue(homePage.isRowValueDisplayed("24128","Albania","25266","49397"));
		Assert.assertTrue(homePage.isRowValueDisplayed("338282","Argentina","349238","687522"));
	}
	
	
	public void TC_04_Icon_Button() {
		homePage.clickToRowActionByCountryName("AFRICA", "remove");
		homePage.clickToRowActionByCountryName("Albania", "remove");
		
		homePage.refreshCurrentPage(driver);
		homePage.clickToRowActionByCountryName("Argentina", "edit");
		homePage.refreshCurrentPage(driver);
		homePage.clickToRowActionByCountryName("Aruba", "edit");
	}
	
	public void TC_05_Get_All_Collumn_Values() {
		homePage.getAllPageValuesByColumnName("Country");
		homePage.getAllPageValuesByColumnName("Total");
		
	}
	
	@Test
	public void TC_06_Action_By_Index() {
		homePage.openPageURL(driver, "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");
		//Input value on column Contact Person and 2nd row
		homePage.enterToTextBoxByColumnNameAndRowIndex("Contact Person","2", "Data Test");
		homePage.enterToTextBoxByColumnNameAndRowIndex("Company","1", "Data Test 2");
		//Select data on Country ddl and 3rd row
		homePage.selectDropDownByColumnNameAndRowIndex("Country","3", "Taiwan");
		
		//Check checkbox NPO and 1st row
		homePage.clickToCheckBoxByColumnNameAndRowIndex("NPO?","1");
	}
	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
