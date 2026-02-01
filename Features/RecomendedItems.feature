Feature: Recommended items

  Scenario: Add to cart from Recommended items

    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify that home page is visible successfully
    Then Scroll down to footer
    Then Verify RECOMMENDED ITEMS are visible
    Then Click on Add To Cart on Recommended product
    And Click View Cart button
