Feature: User Login

  Scenario: Successful Login with Valid Credentials
    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify home page is loaded
    And User Click on the Login link
    Then Verify Login to your account is visible
    And User enter his Email and password
    And User Click on Login button
    Then Verify user is logged in successfully
    And Close Browser


