Feature: Verify Address

  Scenario: Verify address details in checkout page

    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify that home page is visible successfully
    And User Click on the Login link
    And Verify New User Signup is visible
    And Enter name and email address
    And Click Signup button
    And  Fill details: Title, Name, Email, Password, Date of birth
    And Select checkbox Sign up for our newsletter!
    And Select checkbox Receive special offers from our partners!
    And Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
    And Click Create Account button
    And Verify that ACCOUNT CREATED is visible
    And Click Continue button
    And Verify that Logged in as username is visible
    Then Click on Products button
    Then Hover over first product and click Add to cart
    Then Click Continue Shopping button
    And Hover over second product and click Add to cart
    And Click View Cart button
    And Verify both products are added to Cart
    Then Click Proceed To Checkout
  Then Verify that the delivery address is same address filled at the time registration of account

  Then Verify that the billing address is same address filled at the time registration of account
    And Click Delete Account button
    And Verify that ACCOUNT DELETED is visible and click Continue button