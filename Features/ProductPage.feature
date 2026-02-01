Feature: product list and Product page

  Scenario: Verify All Products and product detail page

    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify that home page is visible successfully
    Then Click on Products button
    #Then Verify user is navigated to ALL PRODUCTS page successfully
    Then Click on View Product of first product
    Then  User is landed to product detail page
    And Verify that detail detail is visible: product name, category, price, availability, condition, brand