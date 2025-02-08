package com.jquery.upload;

import org.testng.annotations.Test;
import commons.BaseTest;
import pageObjects.jquery.PageGeneratorManager;
import pageObjects.jquery.UploadPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_14_Upload_File extends BaseTest {
	WebDriver driver;
	UploadPageObject uploadPage;
	String coronImg = "Coron.jpg";
	String elnidoImg = "ElNido.jpg";
	String saigonImg = "SaiGon.jpg";
	
	String[] fileNames = {coronImg,elnidoImg,saigonImg};

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		uploadPage = PageGeneratorManager.getUploadPage(driver);
	}
	
	public void TC_01_Upload_Single_File() {
		uploadPage.uploadMultipleFiles(driver, coronImg);
		uploadPage.sleepInSec(2);
		uploadPage.uploadMultipleFiles(driver, elnidoImg);
		uploadPage.sleepInSec(2);
		
		Assert.assertTrue(uploadPage.isFileLoadedSuccess(coronImg));
		Assert.assertTrue(uploadPage.isFileLoadedSuccess(elnidoImg));
		
		uploadPage.clickStartButtonOnEachFile();
		
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(coronImg));
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(elnidoImg));
		
	}
	
	public void TC_01_Upload_Multiple_Files() {
		uploadPage.refreshCurrentPage(driver);
		uploadPage.uploadMultipleFiles(driver, fileNames);
		uploadPage.sleepInSec(2);
		
		Assert.assertTrue(uploadPage.isFileLoadedSuccess(coronImg));
		Assert.assertTrue(uploadPage.isFileLoadedSuccess(elnidoImg));
		Assert.assertTrue(uploadPage.isFileLoadedSuccess(saigonImg));
		
		uploadPage.clickStartButtonOnEachFile();
		
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(coronImg));
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(elnidoImg));
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(saigonImg));
		
	}
	
	@Test
	public void TC_03_Upload_GoFiles() {
		uploadPage.openPageURL(driver, "https://gofile.io/uploadFiles");
		
		// Wait for loading icon when open page
		Assert.assertTrue(uploadPage.isLoadingIconMainContentDisappear());
		
		// Upload files
		uploadPage.uploadMultipleFiles(driver, fileNames);
		
		// Wait for loading icon when upload file
		Assert.assertTrue(uploadPage.isLoadingIconMainUploadDisappear());
		
		// wait for progress bars are invisible
		Assert.assertTrue(uploadPage.isMultipleProgressBarIconDisappear());
		
		// Wait for successful msg
		Assert.assertTrue(uploadPage.isSuccessMessageDisplayed("Your files have been successfully uploaded"));
		
		// Wait and click Download link
		uploadPage.clickToSuccessLink();
		
		// Wait for table contain uploaded files
		Assert.assertTrue(uploadPage.isContentTableDisplayed());
		
		// Verify files download button
		Assert.assertTrue(uploadPage.isDownloadButtonDisplayed(coronImg));
		Assert.assertTrue(uploadPage.isDownloadButtonDisplayed(elnidoImg));
		Assert.assertTrue(uploadPage.isDownloadButtonDisplayed(saigonImg));
		
		// Verify files play button
		Assert.assertTrue(uploadPage.isPlayButtonDisplayed(coronImg));
		Assert.assertTrue(uploadPage.isPlayButtonDisplayed(elnidoImg));
		Assert.assertTrue(uploadPage.isPlayButtonDisplayed(saigonImg));
	}
	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
