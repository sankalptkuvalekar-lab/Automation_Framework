Feature: Login and checkout

  Scenario: Place Order: Login before Checkout

    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"

    Then Verify home page is loaded

    And User Click on the Login link
    Then Verify Login to your account is visible
    And User enter his Email and password
    And User Click on Login button

    Then Verify user is logged in successfully

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


