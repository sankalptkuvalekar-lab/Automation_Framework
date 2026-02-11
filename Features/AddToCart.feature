Feature: Add product to cart

  Scenario: Adding product to the Cart

    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify home page is loaded
    Then Click on Products button
    Then Click on View Product of first product
    Then  User is landed to product detail page
    And Verify that detail detail is visible: product name, category, price, availability, condition, brand
    Then Click on Add To Cart Button
    And Click on the View Cart link of the popup
    And Verify their prices, quantity and total price