Feature: Cart

  Scenario: Add Products in Cart

    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify that home page is visible successfully
    Then Click on Products button
    Then Hover over first product and click Add to cart
    Then Click Continue Shopping button
    And Hover over second product and click Add to cart
    And Click View Cart button
    And Verify both products are added to Cart
    And Verify their prices, quantity and total price


