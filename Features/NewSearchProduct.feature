Feature: Searching Product

  Scenario: Search Product
    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify that home page is visible successfully
    Then Click on Products button
    #Then Verify user is navigated to ALL PRODUCTS page successfully
    Then Enter product name in search input and click search button
    Then Verify SEARCHED PRODUCTS is visible
    Then Verify all the products related to search are visible