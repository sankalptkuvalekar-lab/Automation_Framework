Feature: Scrolling Functionality

  Scenario: Verify Scroll Up with 'Arrow' button and Scroll Down functionality


    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify that home page is visible successfully
    Then Scroll down to footer
    Then Verify text SUBSCRIPTION
    And Click on arrow at bottom right side to move upward
   Then Verify that page is scrolled up and Full-Fledged practice website for Automation Engineers text is visible on screen