package StepDefinations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import actions.WebDriver_Helper;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import objectRepository.GoogleSearch_All_Page;
import objectRepository.MainLandingPage_ObjRepo;
import objectRepository.NewsLandingPage_ObjRepo;

public class News_Steps {

	Scenario thisscenario;

	@Before
	public void beforeScenario(Scenario s) {
		thisscenario =s;

	}	

	MainLandingPage_ObjRepo mainpage = new MainLandingPage_ObjRepo();
	NewsLandingPage_ObjRepo newspage = new NewsLandingPage_ObjRepo();
	GoogleSearch_All_Page  googlesearch = new GoogleSearch_All_Page();

	WebDriver driver ;
	private boolean allmenupresent;
	public ArrayList<String> newswithtitle;
	public List<WebElement> googleAlltitle;
	public List<WebElement> googleAlltime;
	public List<WebElement> googleAllheaders;
	public String guardianwindowhandle;
	public String googlewindowhandle;
	public String finalwindw;

	@Given("user launched The Guardian Home Page")
	public void user_launched_The_guardian_home_page() {


	}

	@When("user see the title of the page is and cookies message on home page")
	public void user_see_the_title_of_the_page_is_and_cookies_message_on_home_page(io.cucumber.datatable.DataTable dataTable) {

		List<String> url = dataTable.columns(0).asList();

		mainpage.LaunchtheNewsPage(url.get(0));
		String title = 	mainpage.getTheNewsPageTitle();

		thisscenario.log(title);

	}

	@Then("verify the page title and accept all cookies")
	public void verify_the_page_title_and_accept_all_cookies() {

		mainpage.acceptcookies();
	}


	@When("user sees the get support window")
	public void user_sees_the_get_support_window() {

	}

	@Then("click the close button of support window")
	public void click_the_close_button_of_support_window() {
		mainpage.closeTheSupportwindow();
		thisscenario.log("close the support window ");


	}

	@Then("verify the current date")
	public void verify_the_current_date() {

		String actuadate =	mainpage.verifyDate();
		LocalDate myObj = LocalDate.now(); 
		String expectdate =  myObj.toString();	
		Assertions.assertEquals(expectdate, actuadate);
		thisscenario.log("verified Actual date and Expected date");
		thisscenario.log(" Actual date:"+actuadate);
		thisscenario.log(" Expected date:"+expectdate);
	}

	@When("user looks for main menu items")
	public void user_looks_for_main_menu_items() {
		allmenupresent = 	newspage.verifyTheMenuItems();	


	}

	@Then("validate all the menus with values are present")
	public void validate_all_the_menus_with_values_are_present() {

		Assertions.assertTrue(allmenupresent);
		thisscenario.log(" Verified the menu items ");
		finalwindw= WebDriver_Helper.getcurrrentwindowhandle();

	}

	@When("user reads the displayed news")
	public void user_reads_the_displayed_news() {

		newswithtitle = newspage.readTheNewsHeaders();
		newswithtitle.stream().forEach(n->System.out.println(n));
		guardianwindowhandle= WebDriver_Helper.getcurrrentwindowhandle();

	}


	@Then("verifies in the search results for corrcetness in google with title positoned at {int}")
	public void verifies_in_the_search_results_for_corrcetness_in_google_with_title_positoned_at(Integer position) {

		googlesearch.searchForTheNews(newswithtitle.get(position -1));
		thisscenario.log("Verifying the news for :"+newswithtitle.get(position -1));

	}




	@Then("verfiy the news with position {int} is real")
	public void verfiy_the_news_with_position_is_real(Integer list) {

		googleAlltitle = 	googlesearch.getTheAllnewsheader();

		googleAlltime = 	googlesearch.getTheTimestampofAllnews();

		googleAllheaders = 	googlesearch.getTheAllNewsHeadersExpanded();


		ArrayList<Double> newsmatch = new ArrayList<>();
		for(int i=0;i<googleAllheaders.size();i++)
		{
			double adq  =	NewsSimilarity.similarity(newswithtitle.get(list-1), googleAllheaders.get(i).getText());
			newsmatch.add(Double.valueOf(adq));
		}


		for(int i=0;i<googleAlltitle.size();i++)
		{
			double adq  =	NewsSimilarity.similarity(newswithtitle.get(list-1), googleAlltitle.get(i).getText());

			newsmatch.add(Double.valueOf(adq));
		}

		long news = newsmatch.stream().filter(n->n>0.25).count();

		
//		googlewindowhandle = WebDriver_Helper.getcurrrentwindowhandle();
//		driver.close();
//
//		driver.switchTo().window(guardianwindowhandle);

	

		if(newsmatch.stream().filter(n->n>0.25).count() >= 5)
		{
			thisscenario.log("The news similar on google search is "+ news);

		}
		else
		{
			thisscenario.log("The news is fake ");
			Assertions.fail();
		}


		System.out.println(newsmatch.stream().filter(n->n>0.25).count() +"********************");

	}

	@Then("if the timestamp of the reslt news is more than a day identify as dead news")
	public void if_the_timestamp_of_the_reslt_news_is_more_than_a_day_identify_as_dead_news() {
		googleAlltitle = 	googlesearch.getTheAllnewsheader();

		googleAlltime = 	googlesearch.getTheTimestampofAllnews();

		googleAllheaders = 	googlesearch.getTheAllNewsHeadersExpanded();


		long hrcount = 	googleAlltime.stream().filter(n->n.getText().contains("hours ago")).count();
		long minutescount = 	googleAlltime.stream().filter(n->n.getText().contains("minutes ago")).count();
		long seccount = 	googleAlltime.stream().filter(n->n.getText().contains("sec ago")).count();

//		googlewindowhandle = WebDriver_Helper.getcurrrentwindowhandle();
//		driver.close();
//
//		driver.switchTo().window(guardianwindowhandle);


		if(hrcount+minutescount+seccount >  2 )
		{
			System.out.println("the news is latest and not older than a day ago");
			thisscenario.log("the news is latest and not older than a day ago");



		}
		else
		{
			System.out.println("the news is older by a day");
			thisscenario.log("the news is not latest so it might be fake ");
		}



	}

	@When("want to validate the news is displayed")
	public void want_to_validate_the_news_is_displayed() {

		if(newswithtitle.size() >0 )
		{
			System.out.println("the news is found ");
			thisscenario.log("the news is found ");

		}

	}

	@Then("user copies the selected news header details in new window")
	public void user_copies_the_selected_news_header_details_in_new_window() {
		googlesearch.navigatetonewtab();


		try {

			googlesearch.googleAcceptcookies();

		}
		catch (NoSuchElementException e) {

		}

	}


	@After
	public void teardown() {

	}	



}
