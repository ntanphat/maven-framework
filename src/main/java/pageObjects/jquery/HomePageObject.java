package pageObjects.jquery;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jquery.HomePageUI;

public class HomePageObject extends BasePage {
	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputToColumnTextboxByName(String columnName, String value) {
		waitForElementVisible(driver, HomePageUI.COLUMN_TEXT_BOX_BY_NAME, columnName);
		sendKeyToElement(driver, HomePageUI.COLUMN_TEXT_BOX_BY_NAME, value, columnName);
	}
	
	public void clickToPageNumber(String pageNumber) {
		waitForElementClickable(driver, HomePageUI.PAGE_LINK_BY_NUMBER, pageNumber);
		clickToElement(driver, HomePageUI.PAGE_LINK_BY_NUMBER, pageNumber);
	}
	
	public boolean isPageActiveByNumber(String pageNumber) {
		waitForElementVisible(driver, HomePageUI.PAGE_LINK_ACTIVE_BY_NUMBER, pageNumber);
		return isElementDisplay(driver, HomePageUI.PAGE_LINK_ACTIVE_BY_NUMBER, pageNumber);
	}
	
	public boolean isRowValueDisplayed(String female, String country, String male, String total) {
		waitForElementVisible(driver, HomePageUI.DYNAMIC_ROW_VALUES, female, country, male, total);
		return isElementDisplay(driver, HomePageUI.DYNAMIC_ROW_VALUES, female, country, male, total);
	}
	
	public void clickToRowActionByCountryName(String countryName, String rowAction) {
		waitForElementClickable(driver, HomePageUI.ROW_ACTION_BY_COUNTRY_NAME, countryName, rowAction);
		clickToElement(driver, HomePageUI.ROW_ACTION_BY_COUNTRY_NAME, countryName, rowAction);
		
	}

	public void getAllPageValuesByColumnName(String columnName) {
		List<String> allValues = new ArrayList<String>();
		List<WebElement> allPageLinks = getListWebElement(driver, HomePageUI.ALL_PAGE_LINKS);
		int columnIndex = getListElementSize(driver, HomePageUI.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		
		for (WebElement pageLink : allPageLinks) {
			pageLink.click();
			sleepInSec(2);
			List<WebElement> allRowValues = getListWebElement(driver, HomePageUI.ALL_VALUE_BY_COLUMN_INDEX, String.valueOf(columnIndex) );
			for (WebElement rowValue : allRowValues) {
				allValues.add(rowValue.getText());
			}
		}
		
		for (String value : allValues) {
			System.out.println(value);
		}
	}

	public void enterToTextBoxByColumnNameAndRowIndex(String columnName, String rowIndex, String value) {
		int columnIndex = getListElementSize(driver, HomePageUI.COLUMN_INDEX_BY_COLUMN_NAME_2, columnName) + 1;
		waitForElementVisible(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex, String.valueOf(columnIndex));
		sendKeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, value, rowIndex, String.valueOf(columnIndex));
	}

	public void selectDropDownByColumnNameAndRowIndex(String columnName, String rowIndex, String value) {
		int columnIndex = getListElementSize(driver, HomePageUI.COLUMN_INDEX_BY_COLUMN_NAME_2, columnName) + 1;
		waitForElementVisible(driver, HomePageUI.DYNAMIC_DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex, String.valueOf(columnIndex));
		selectItemInDefaultDropdown(driver, HomePageUI.DYNAMIC_DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, value, rowIndex, String.valueOf(columnIndex));
	}

	public void clickToCheckBoxByColumnNameAndRowIndex(String columnName, String rowIndex) {
		int columnIndex = getListElementSize(driver, HomePageUI.COLUMN_INDEX_BY_COLUMN_NAME_2, columnName) + 1;
		waitForElementVisible(driver, HomePageUI.DYNAMIC_CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex, String.valueOf(columnIndex));
		checkToElement(driver, HomePageUI.DYNAMIC_CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex, String.valueOf(columnIndex));
		
	}
}
