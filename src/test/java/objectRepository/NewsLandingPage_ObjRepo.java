package objectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import actions.WebDriver_Helper;

public class NewsLandingPage_ObjRepo {

	WebDriver driver;

	String _news = "//a[contains(@class,'pillar-link--News')]";
	String _opinions = "//a[contains(@class,'pillar-link--Opinion')]";
	String _sport = "//a[contains(@class,'pillar-link--Sport')]";
	String _culture = "//a[contains(@class,'pillar-link--Culture')]";
	String _lifestye = "//a[contains(@class,'pillar-link--Lifestyle')]";
	String _more = "//button[@id=\"main-menu-toggle\"]/following-sibling::label";

	By item_news = By.xpath(_news);
	By item_opinions = By.xpath(_opinions);
	By item_sport = By.xpath(_sport);
	By item_culture = By.xpath(_culture);
	By item_lifestye = By.xpath(_lifestye);
	By item_more = By.xpath(_more);

	By news_tilescontents = By.xpath("//div[@class='fc-item__content ']/following-sibling::a");

	
	public NewsLandingPage_ObjRepo() {
		this.driver = WebDriver_Helper.getDriver();
	}


	public boolean verifyTheMenuItems()
	{

		boolean flag =false;
		try {
			driver.findElement(item_news);
			driver.findElement(item_opinions);
			driver.findElement(item_sport);
			driver.findElement(item_culture);
			driver.findElement(item_lifestye);
			driver.findElement(item_more);
			flag= true;
		}
		catch (NoSuchElementException e) {

		}

		return flag;

	}

	public ArrayList<String> readTheNewsHeaders()
	{
		List<WebElement>  newsheaders = driver.findElements(news_tilescontents);
		ArrayList<String> _newsheaderstext = new ArrayList<>();

		for(int i=0;i<newsheaders.size();i++)
		{
			_newsheaderstext.add(newsheaders.get(i).getAttribute("text"));


		}
		return _newsheaderstext;

	}



	public NewsLandingPage_ObjRepo getTheNewsObject()
	{
		return this;
	}


}
