Feature: Product Quantity

  Scenario: Verify Product quantity in Cart

    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify that home page is visible successfully
    Then Click on Products button
    Then Click on View Product of first product
    Then  User is landed to product detail page
    And Verify that detail detail is visible: product name, category, price, availability, condition, brand
    When User increases quantity to "4"
    Then Click on Add To Cart Button
    And Click on the View Cart link of the popup
    Then Verify that product quantity is "4" in cart