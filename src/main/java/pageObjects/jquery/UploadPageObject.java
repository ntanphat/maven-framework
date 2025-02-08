package pageObjects.jquery;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.collections.Lists;

import commons.BasePage;
import pageUIs.jquery.UploadPageUI;

public class UploadPageObject extends BasePage{
	WebDriver driver;

	public UploadPageObject(WebDriver driver) {	
		this.driver = driver;
	}

	public boolean isFileLoadedSuccess(String fileName) {
		waitForElementVisible(driver, UploadPageUI.FILE_LOADED_BY_NAME, fileName);
		return isElementDisplay(driver, UploadPageUI.FILE_LOADED_BY_NAME, fileName);
	}

	public void clickStartButtonOnEachFile() {
		List<WebElement> startButtons = getListWebElement(driver, UploadPageUI.MULTIPLE_START_BUTTON);
		for (WebElement startButton : startButtons) {
			waitForElementClickable(driver, startButton);
			clickToElement(driver, startButton);
			sleepInSec(2);
		}
	}

	public boolean isFileUploadedSuccess(String fileName) {
		waitForElementVisible(driver, UploadPageUI.FILE_UPLOADED_BY_NAME, fileName);
		return isElementDisplay(driver, UploadPageUI.FILE_UPLOADED_BY_NAME, fileName);
	}

	public boolean isLoadingIconMainContentDisappear() {
		return waitForElementInvisible(driver, UploadPageUI.SPIN_BORDER_ICON_AT_MAIN_CONTENT);
	}

	public boolean isLoadingIconMainUploadDisappear() {
		return waitForElementInvisible(driver, UploadPageUI.SPIN_BORDER_ICON_AT_MAIN_UPLOAD);
	}

	public boolean isMultipleProgressBarIconDisappear() {
		return waitListElementInvisible(driver, UploadPageUI.MULTIPLE_PROGESS_BAR_ICON);
	}

	public boolean isSuccessMessageDisplayed(String successMessage) {
		waitForElementVisible(driver, UploadPageUI.UPLOAD_SUCCESS_MESSAGE, successMessage);
		return isElementDisplay(driver, UploadPageUI.UPLOAD_SUCCESS_MESSAGE, successMessage);
	}

	public void clickToSuccessLink() {
		waitForElementClickable(driver, UploadPageUI.UPLOAD_SUCCESS_LINK);
		clickToElement(driver, UploadPageUI.UPLOAD_SUCCESS_LINK);
	}

	public boolean isContentTableDisplayed() {
		waitForElementVisible(driver, UploadPageUI.CONTENT_TABLE);
		return isElementDisplayed(driver, UploadPageUI.CONTENT_TABLE);
	}

	public boolean isDownloadButtonDisplayed(String fileName) {
		waitForElementVisible(driver, UploadPageUI.DOWNLOAD_BUTTON_BY_FILE_NAME, fileName);
		return isElementDisplay(driver, UploadPageUI.DOWNLOAD_BUTTON_BY_FILE_NAME, fileName);
	}

	public boolean isPlayButtonDisplayed(String fileName) {
		waitForElementVisible(driver, UploadPageUI.PLAY_BUTTON_BY_FILE_NAME, fileName);
		return isElementDisplay(driver, UploadPageUI.PLAY_BUTTON_BY_FILE_NAME, fileName);
	}

}
