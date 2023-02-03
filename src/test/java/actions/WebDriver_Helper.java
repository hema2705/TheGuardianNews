package actions;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;



public class WebDriver_Helper {
	
	private static WebDriver_Helper helper;
	private static WebDriver driver;
	private  WebDriver_Helper() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/TestDrivers/chromedriver");          
		driver = new ChromeDriver();

		Dimension dem = new Dimension(500,900);
		driver.manage().window().setSize(dem);	}
	
	
	
	
	
	public static void goToUrl(String url) {
		driver.get(url);
	}
	
	public static String getCurrentPageUrl() {
		return driver.getCurrentUrl();
	}
	
	public static String getCurrentPageTitle() {
		return driver.getTitle();
	}
	
	public static String getcurrrentwindowhandle()
	{
	  return 	driver.getWindowHandle();
	}
		
	
	public static void opennewwindowTab()
	{
		
	}
	
	
	public static void setupDriver()
	{
		
		if(driver == null)
		{
			helper =  new WebDriver_Helper();
		}
	}

	public static WebDriver getDriver() {
		
		return driver;
	}

	public void opennewwindowTab(String string) {

	
		driver.switchTo().newWindow(WindowType.TAB).navigate().to(string);
		
	}
}
