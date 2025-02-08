package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class RewardPointsPageObject extends MyAccountSideBarPageObject{
	WebDriver driver;

	public RewardPointsPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}
