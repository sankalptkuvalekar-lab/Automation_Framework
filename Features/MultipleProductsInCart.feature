Feature: Cart

  Scenario: Add Products in Cart

    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify home page is loaded
    Then Click on Products button
    Then Click Add to cart for first product
    Then Click Continue Shopping button
    And Click Add to cart for second product
    And Click View Cart button
    And Verify both products are added to Cart
    And Verify their prices, quantity and total price


