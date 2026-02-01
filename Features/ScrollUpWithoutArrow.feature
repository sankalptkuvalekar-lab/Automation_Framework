Feature: Scrolling Functionality

  Scenario: Verify Scroll Up without 'Arrow' button and Scroll Down functionality


    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify that home page is visible successfully
    Then Scroll down to footer
    Then Verify text SUBSCRIPTION
    Then Scroll up page to top
   Then Verify that page is scrolled up and Full-Fledged practice website for Automation Engineers text is visible on screen