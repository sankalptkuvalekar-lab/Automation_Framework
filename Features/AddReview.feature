Feature: adding Review

  Scenario: Add review on product

    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify that home page is visible successfully
    Then Click on Products button
    Then Click on View Product of first product
   Then Verify 'Write Your Review' is visible
   Then Enter name, email and review
  Then Click on Submit button
    Then Verify success message 'Thank you for your review.'