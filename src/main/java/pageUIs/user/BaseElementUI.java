package pageUIs.user;

public class BaseElementUI {
	//dynamic link for page on header (before and after login)
	public static final String DYNAMIC_HEADER_LINK_BY_NAME = "xpath=//div[@class='header-links']//a[contains(string(),'%s')]";
	public static final String NOP_COMMERCE_LOGO = "xpath=//div[@class='header-logo']//img";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
	public static final String DYNAMIC_TEXTBOX_ERROR_MSG_BY_ID = "xpath=//span[@id='%s-error']";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
}
