Feature: checkout

  Scenario: Register before Checkout

    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify home page is loaded
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
    Then Click Add to cart for first product
    Then Click Continue Shopping button
    And Click Add to cart for second product
    And Click View Cart button
    And Verify both products are added to Cart
    Then Click Proceed To Checkout
    Then Verify Address Details and Review Your Order
    Then Enter description in comment text area and click Place Order
    Then Enter payment details: Name on Card, Card Number, CVC, Expiration date
    And Click Pay and Confirm Order button
    And Verify success message Your order has been placed successfully
    And Click Delete Account button
    And Verify that ACCOUNT DELETED is visible and click Continue button