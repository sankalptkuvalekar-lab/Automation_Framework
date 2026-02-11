Feature: subscription in cart page

  Scenario: Verify Subscription in cart page

    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify home page is loaded
    Then Click Cart button
    Then Scroll down to footer
    Then Verify text SUBSCRIPTION
    And Enter email address in input and click arrow button
    And  Verify success message You have been successfully subscribed is visible