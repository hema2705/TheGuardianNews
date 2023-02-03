package objectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import actions.WebDriver_Helper;

public class GoogleSearch_All_Page {

	WebDriver driver;
	WebDriver_Helper webdriverhelper ;

	By txt_search = By.name("q");
	By tab_All = By.xpath("//a[text()='All']");
	By tab_News = By.xpath("//a[text()=\"News\"]");

	
	By btn_acceptallcookies = By.xpath("//div[text()=\"Accept all\"]");

	
	By googleallnewstitle = By.xpath("//h3[@class=\"LC20lb MBeuO DKV0Md\"]");
	
	By googleallnewstime = By.xpath("//span[@class=\"MUxGbd wuQ4Ob WZ8Tjf\"]/span");
	
	By googlenewsmoredesc = By.xpath("//span[@class=\"MUxGbd wuQ4Ob WZ8Tjf\"]/following-sibling::span | //div[@class=\"VwiC3b yXK7lf MUxGbd yDYNvb lyLwlc lEBKkf\"]/span");
	
	public GoogleSearch_All_Page() {
		this.driver = WebDriver_Helper.getDriver();

		
	}

	public void navigatetonewtab()
	{

		driver.navigate().to("https://www.google.com/");
	}

	public void searchForTheNews(String news)
	{
		driver.findElement(txt_search).sendKeys(news+Keys.ENTER);
	}

	
	public void clicktheAllTab()
	{
		driver.findElement(tab_All).click();

	}
	
	public void ReadtheNewsDispalyed()
	{
		driver.findElement(tab_All).click();

	}
	
	
	public void googleAcceptcookies()
	{
		driver.findElement(btn_acceptallcookies).click();
	}
	
	
	public List<WebElement> getTheAllnewsheader()
	{
		return 	driver.findElements(googleallnewstitle);
	}

	
	public List<WebElement> getTheTimestampofAllnews()
	{
		return	driver.findElements(googleallnewstime);
	}

	
	public void clickonNewsTab()
	{
		driver.findElement(tab_News);
	}
	
	
	public List<WebElement> getTheAllNewsHeadersExpanded()
	{
		return driver.findElements(googlenewsmoredesc);
		
	}

	
	
}
