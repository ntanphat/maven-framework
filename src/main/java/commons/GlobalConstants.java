package commons;

import java.io.File;

public class GlobalConstants {
	//server url: dev/ test/ sit/ uat
	//database: dev/ sit/ uat
	//timeout
	//username, password
	//3rd: sandbox
	//download/ upload folder
	//related project path
	//OS name
	//Cloud Testing: BrowserStack, SourceLab,...
	public static final String DEV_USER_URL = "https://demo.nopcommerce.com/";
	public static final String DEV_ADMIN_URL = "https://admin-demo.nopcommerce.com/";
	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 30;
	public static final String DEV_ADMIN_USERNAME = "admin@yourstore.com";
	public static final String DEV_ADMIN_PASSWORD = "admin";
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	public static final String RELATIVE_PROJECT_PATH = System.getProperty("user.dir") + File.separator + "src";
	public static final String UPLOAD_PATH = RELATIVE_PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String DOWNLOAD_PATH = RELATIVE_PROJECT_PATH + File.separator + "downloadFiles" + File.separator;
	public static final String REPORTNG_IMAGE_PATH = RELATIVE_PROJECT_PATH + File.separator + "reportNGImage" + File.separator;
	public static final String RESOURCE_PATH = RELATIVE_PROJECT_PATH + File.separator + "test" + File.separator + "resources" + File.separator;

}
