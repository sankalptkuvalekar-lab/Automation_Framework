Feature: brand products

  Scenario: View & Cart Brand Products

    Given User Launch Chrome Browser
    When User open the URL "https://automationexercise.com/"
    Then Verify that home page is visible successfully
    Then Click on Products button
      Then Verify that Brands are visible on left side bar
    And Click on "Polo" brand name
  Then Verify that user is navigated to brand page and brand products are displayed
  Then On left side bar, click on any other brand link