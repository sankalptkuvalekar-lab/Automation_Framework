Feature: Category Products

  Scenario: View Category Products

    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify that categories are visible on left side bar
     And Click on Women category
    Then Click on any category link under 'Women' category, for example: Dress
    Then Verify that category page is displayed and confirm text "WOMEN - DRESS PRODUCTS"
    Then On left side bar, click on any sub-category link of 'Men' category
    Then Verify that user is navigated to that category page

