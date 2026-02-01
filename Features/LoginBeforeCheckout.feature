Feature: Login and checkout

  Scenario:  Place Order: Login before Checkout

    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify that home page is visible successfully
    And User Click on the Login link
    Then Verify Login to your account is visible
    And User enter his Email and password
    And User Click on Login button
    Then Verify that home page is visible successfully
    Then Click on Products button
    Then Hover over first product and click Add to cart
    Then Click Continue Shopping button
    And Hover over second product and click Add to cart
    And Click View Cart button
    And Verify both products are added to Cart
    Then Click Proceed To Checkout
    Then  Click RegisterLogin button
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
    Then Click Cart button
    Then Click Proceed To Checkout
    Then Verify Address Details and Review Your Order
    Then Enter description in comment text area and click Place Order
    Then Enter payment details: Name on Card, Card Number, CVC, Expiration date
    And Click Pay and Confirm Order button
    And Verify success message Your order has been placed successfully
    And Click Delete Account button
    And Verify that ACCOUNT DELETED is visible and click Continue button
