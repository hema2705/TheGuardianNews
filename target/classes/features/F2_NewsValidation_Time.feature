Feature: The Guardian News headlines are latest 
     @acceptcookies
    Scenario: View the news portal Launched 
    Given  user launched The Guardian Home Page 
    When user see the title of the page is and cookies message on home page 
    |https://www.theguardian.com/tone/news|  
    Then verify the page title and accept all cookies 
    
    @closesupport
  Scenario:  Close the support 
    Given  user launched The Guardian Home Page 
    When  user sees the get support window 
    Then  click the close button of support window
    And   verify the current date 
  
  
  Scenario Outline: Verify the guardian news timestamp 
    Given user launched The Guardian Home Page
    When user reads the displayed news
    And want to validate the news is displayed
    Then user copies the selected news header details in new window 
    And  verifies in the search results for corrcetness in google with title positoned at <Headlines column number>
    But if the timestamp of the reslt news is more than a day identify as dead news 
  
	Examples:
	|Headlines column number|
  |1|