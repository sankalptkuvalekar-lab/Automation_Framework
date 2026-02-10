Feature: Home Page Subscription

  Scenario: Verify Subscription in home page

    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify that home page is visible successfully
    Then Scroll down to footer
    Then Verify text SUBSCRIPTION
    And User enters email "test@example.com" in subscription input
    And User clicks on subscribe button
    Then User should see success message "You have been successfully subscribed!"