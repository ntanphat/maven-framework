package commons;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;
import pageUIs.user.BasePageUI;

public class BasePage {
	// static ko cần khởi tạo mà vẫn có thể truy cập trong phạm vi class
	public static BasePage getBasePage() {
		return new BasePage();
	}

	/* Web Browser Functions */
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

	@Step("Refresh page")
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
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

	public Set<Cookie> getBrowserCookies(WebDriver driver) {
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

	/* Element Functions */
	public By getByXpath(String locator) {
		return By.xpath(locator);
	}

	public String getDynamicLocator(String locator, String... restParams) {
		//System.out.println(String.format(locator, (Object[]) restParams));
		return String.format(locator, (Object[]) restParams);
	}

	public By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPath=") || locatorType.startsWith("Xpath=")
				|| locatorType.startsWith("XPATH=")) {
			by = By.xpath(locatorType.substring(6));
		} else if (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("id=") || locatorType.startsWith("Id=") || locatorType.startsWith("ID=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("name=") || locatorType.startsWith("Name=")
				|| locatorType.startsWith("NAME=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("Class=")
				|| locatorType.startsWith("CLASS=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("tagname=") || locatorType.startsWith("Tagname=")
				|| locatorType.startsWith("TAGNAME=") || locatorType.startsWith("TagName=")) {
			by = By.tagName(locatorType.substring(8));
		} else {
			throw new RuntimeException("Locator type is invalid.");
		}
		return by;
	}

	public WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getByLocator(locator));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String locator) {
		return driver.findElements(getByLocator(locator));
	}
	
	public List<WebElement> getListWebElement(WebDriver driver, String locator, String... restParams) {
		return driver.findElements(getByLocator(getDynamicLocator(locator, restParams)));
	}

	public void clickToElement(WebDriver driver, String locator) {
		getWebElement(driver, locator).click();
	}
	
	public void clickToElement(WebDriver driver, WebElement element) {
		element.click();
	}

	public void clickToElement(WebDriver driver, String locator, String... restParams) {
		getWebElement(driver, getDynamicLocator(locator, restParams)).click();
	}

	public void sendKeyToElement(WebDriver driver, String locator, String value) {
		getWebElement(driver, locator).clear();
		getWebElement(driver, locator).sendKeys(value);
	}

	public void sendKeyToElement(WebDriver driver, String locator, String value, String... restParams) {
		getWebElement(driver, getDynamicLocator(locator, restParams)).clear();
		getWebElement(driver, getDynamicLocator(locator, restParams)).sendKeys(value);
	}

	public String getElementText(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText();
	}

	public String getElementText(WebDriver driver, String locator, String... restParams) {
		return getWebElement(driver, getDynamicLocator(locator, restParams)).getText();
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String visibleText) {
		new Select(getWebElement(driver, locator)).selectByVisibleText(visibleText);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String visibleText, String... restParams) {
		new Select(getWebElement(driver, getDynamicLocator(locator, restParams))).selectByVisibleText(visibleText);
	}

	public String getFirstSelectedTextInDefaultDropdonw(WebDriver driver, String locator) {
		return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
	}

	public boolean isDefaultDropdownMultiple(WebDriver driver, String locator) {
		return new Select(getWebElement(driver, locator)).isMultiple();
	}

	public void selectItemInCustomDropDown(WebDriver driver, String parentLocator, String childLocator,
			String expectedItem) {
		getWebElement(driver, parentLocator).click();
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));

		List<WebElement> speedDropDownItems = getListWebElement(driver, childLocator);

		for (WebElement tmpItem : speedDropDownItems) {
			if (tmpItem.getText().trim().equals(expectedItem)) {
				tmpItem.click();
				break;
			}
		}
	}

	public String getElementAttributeValue(WebDriver driver, String locator, String attributeName) {
		return getWebElement(driver, locator).getAttribute(attributeName);
	}
	
	public String getElementAttributeValue(WebDriver driver, String locator, String attributeName, String... restParams) {
		return getWebElement(driver, getDynamicLocator(locator, restParams)).getAttribute(attributeName);
	}

	public String getElementCssValue(WebDriver driver, String locator, String cssPropertyName) {
		return getWebElement(driver, locator).getCssValue(cssPropertyName);
	}

	public String convertRGBAToHexaColor(WebDriver driver, String locator) {
		String backgroundColorRGBA = getElementCssValue(driver, locator, "background-color");
		return Color.fromString(backgroundColorRGBA).asHex();
	}

	public int getListElementSize(WebDriver driver, String locator) {
		return getListWebElement(driver, locator).size();
	}
	
	public int getListElementSize(WebDriver driver, String locator, String... restParams) {
		return getListWebElement(driver, getDynamicLocator(locator, restParams)).size();
	}

	/**
	 * Apply for checkbox and radio button
	 * 
	 * @param driver
	 * @param locator
	 */
	public void checkToElement(WebDriver driver, String locator) {
		if (!getWebElement(driver, locator).isSelected()) {
			getWebElement(driver, locator).click();
		}
	}
	
	public void checkToElement(WebDriver driver, String locator, String... restParams) {
		if (!getWebElement(driver, getDynamicLocator(locator, restParams)).isSelected()) {
			getWebElement(driver, getDynamicLocator(locator, restParams)).click();
		}
	}

	/**
	 * Apply for checkbox
	 * 
	 * @param driver
	 * @param locator
	 */
	public void unCheckToElement(WebDriver driver, String locator) {
		if (getWebElement(driver, locator).isSelected()) {
			getWebElement(driver, locator).click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isDisplayed();
	}

	public boolean isElementDisplay(WebDriver driver, String locator, String... restParams) {
		return getWebElement(driver, getDynamicLocator(locator, restParams)).isDisplayed();
	}

	public void setImplicitWaitTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
	}
	
	public boolean isElementUnDisplayed(WebDriver driver, String locator) {
		setImplicitWaitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, locator);
		setImplicitWaitTimeout(driver, longTimeout);
			if (elements.size() == 0) { // elements not exists in DOM
				return true;
			} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
				//element exists in DOM and not Displayed
				return true;
			} else {
				//element exist in DOM
				return false;
			}
		}
	
	public boolean isElementSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}

	public void switchToIframe(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout))
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getByLocator(locator)));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverToElement(WebDriver driver, String locator) {
		new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
	}

	public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		new Actions(driver).dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator))
				.perform();
	}

	public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key) {
		new Actions(driver).sendKeys(getWebElement(driver, locator), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		return ((JavascriptExecutor) driver).executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		String textActual = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSec(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
	}

	public void scrollToElementOnTop(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				getWebElement(driver, locator));
	}

	public void scrollToElementOnDown(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",
				getWebElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')",
				getWebElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(driver, locator));
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;",
				getWebElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getWebElement(driver, locator));
		return status;
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout))
				.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... restParams) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(
				ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicLocator(locator, restParams))));
	}

	public void waitListElementVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout))
				.until(ExpectedConditions.visibilityOfAllElements(getListWebElement(driver, locator)));
	}

	public boolean waitForElementInvisible(WebDriver driver, String locator) {
		return new WebDriverWait(driver, Duration.ofSeconds(longTimeout))
				.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
	}

	public boolean waitListElementInvisible(WebDriver driver, String locator) {
		return new WebDriverWait(driver, Duration.ofSeconds(longTimeout))
				.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locator)));
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout))
				.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
	}
	
	public void waitForElementClickable(WebDriver driver, WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout))
				.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementClickable(WebDriver driver, String locator, String... restParams) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout))
				.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicLocator(locator, restParams))));
	}

	public boolean isPageLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String filePath = GlobalConstants.UPLOAD_PATH;
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, BasePageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName);
	}
	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
}
