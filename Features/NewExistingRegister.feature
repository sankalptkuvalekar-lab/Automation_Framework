Feature: Existing Register

  Scenario: Register User with existing email

    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify that home page is visible successfully
    And User Click on the Login link
    And Verify New User Signup is visible
    And Enter name and already registered email address
    And Click Signup button
    Then Verify error Email Address already exist! is visible
    And Close Browser