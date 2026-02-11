Feature: Contact Us
  Scenario: Contact Us Form

    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify home page is loaded
    Then Click on Contact Us button
    Then Verify CONTACT US is visible
    Then Enter name, email, subject and message

    Then Click Submit button
    Then Click OK button
    Then Verify success message Success! Your details have been submitted successfully. is visible
     And Click Home button and verify that landed to home page successfully
