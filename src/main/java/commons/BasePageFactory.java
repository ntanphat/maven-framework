package commons;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageFactory {
	public void openPageURL(WebDriver driver, String pageUrl) {
		// Biến cục bộ
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentPageURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptToAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
		// driver.switchTo().alert().accept();
	}

	public void cancelToAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
		// driver.switchTo().alert().dismiss();
	}

	public String getTextInAlert(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
		// return driver.switchTo().alert().getText();
	}

	public void sendKeyToAlert(WebDriver driver, String keysToSend) {
		waitForAlertPresence(driver).sendKeys(keysToSend);
		// driver.switchTo().alert().sendKeys(keysToSend);
	}

	public void closeAllWindowExceptID(WebDriver driver, String windowID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(windowID);
	}

	public void switchToWindowByID(WebDriver driver, String windowID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				sleepInSec(1);
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			driver.switchTo().window(id);
			sleepInSec(1);
			String windowTitle = driver.getTitle();
			if (title.equals(windowTitle)) {
				break;
			}
		}

	}

	public void sleepInSec(long timeInSec) {
		try {
			Thread.sleep(timeInSec * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Set<Cookie> getBrowserSize(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
	}

	public void deleteAllCookies(WebDriver driver) {
		driver.manage().deleteAllCookies();
	}
	
	public void clickToElement(WebDriver driver, WebElement element) {
		element.click();
	}
	
	public void sendKeyToElement(WebDriver driver, WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}
	
	public String getElementText(WebDriver driver, WebElement element) {
		return element.getText();
	}
	
	public void waitForElementVisible (WebDriver driver, WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementClickable (WebDriver driver, WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public String getElementAttribute(WebDriver driver, WebElement element, String attributeName) {
		return element.getAttribute(attributeName);
	}
}
