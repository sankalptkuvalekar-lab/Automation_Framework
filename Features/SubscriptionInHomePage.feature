Feature: subscription in home page

  Scenario: Verify Subscription in home page

    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify home page is loaded
    Then Scroll down to footer
    Then Verify text SUBSCRIPTION
    And Enter email address in input and click arrow button
    And  Verify success message You have been successfully subscribed is visible