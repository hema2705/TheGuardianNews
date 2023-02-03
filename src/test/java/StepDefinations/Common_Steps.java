package StepDefinations;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import actions.WebDriver_Helper;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Common_Steps {

	 private WebDriver driver;

	@BeforeAll
	public static void before_all()
	{
		WebDriver_Helper.setupDriver();
	}
	
	@AfterStep
	public void tearDown(Scenario scenario) throws Exception {
			//Take Screenshot
		//	final byte[] shot = ((TakesScreenshot)WebDriver_Helper.getDriver()).getScreenshotAs(OutputType.BYTES);
			//Embed into Report
		//	scenario.attach(shot, "image/png", scenario.getName());
			

	}
	
	@Before("@acceptcookies")
    public void beforeFirst(){
        System.out.println("This will run only before the First Scenario");
    }	

	@Before("@closesupport")
    public void beforeSecond(){
        System.out.println("This will run only before the Second Scenario");
    }	

	
	
	
	
}