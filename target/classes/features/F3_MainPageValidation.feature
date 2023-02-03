
  Feature: The Guardian News Main Page basic validations

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


  Scenario: Verify the menu items
    Given user launched The Guardian Home Page
    When user looks for main menu items 
    Then validate all the menus with values are present 
    
    
 