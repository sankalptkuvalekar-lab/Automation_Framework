Feature: User Login

  Scenario: Logout User
    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify that home page is visible successfully
    And User Click on the Login link
    Then Verify Login to your account is visible
    And User enter his Email and password
    #And User enter his "email" and "Password"
    And User Click on Login button
    Then Verify that Logged in as username is visible
    Then Verify that home page is visible successfully
    And click on Logout button

    Then Verify that the user is navigated to the login Page
    And Close Browser

