package objectRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import actions.WebDriver_Helper;

public class MainLandingPage_ObjRepo {

	WebDriver driver;


	By iframetitle  = By.xpath("//iframe[@title=\"The Guardian consent message\"]");

	By btn_imhappy_acceptcookies = By.xpath("//button[@title=\"Yes, I’m happy\"]");

	String newsTitle  = "News | The Guardian";


	By shadowparent_support = By.xpath("//div[@class='site-message--banner remote-banner']");
	By support_close = By.cssSelector("button[class=\"automat-1xbh8nw\"]");
	By todaysdate = By.xpath("//time[1][@class=\"fc-date-headline\"]");



	public MainLandingPage_ObjRepo() {
		this.driver = WebDriver_Helper.getDriver();

	}

	public void LaunchtheNewsPage(String url)
	{
		driver.get(url);
	}


	public String getTheNewsPageTitle()
	{
		return driver.getTitle();	
	}


	public void acceptcookies()
	{
		
		try {
		
		//Store the web element
		WebElement iframe = driver.findElement(By.cssSelector("#sp_message_container_762782>iframe"));

		//Switch to the frame
		driver.switchTo().frame(iframe);

		//Now we can click the button
		driver.findElement(By.cssSelector("button[title=\"Yes, I’m happy\"]")).click();
		  
		 driver.switchTo().defaultContent();
		}
		catch (NoSuchElementException e) {
			
			// TODO: handle exception
		}
	}


	public void closeTheSupportwindow()
	{
		try {

			WebElement root1 = driver.findElement(shadowparent_support).getShadowRoot().findElement(support_close);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", root1);
		}
		catch (NoSuchElementException e) {
			System.out.println("no such element");
		}

	}


	public String verifyDate()
	{

		String actualdate = 	driver.findElement(todaysdate).getAttribute("datetime");
		String date2 =	driver.findElement(todaysdate).getText();

		return actualdate;

	}
}
