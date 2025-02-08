package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class OrdersPageObject extends MyAccountSideBarPageObject{
	WebDriver driver;

	public OrdersPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}
