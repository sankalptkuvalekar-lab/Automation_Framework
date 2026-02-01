Feature: User Login


  Scenario: Successful Login with Valid Credentials
    Given User Launch Chrome Browser
    When User open the URL "https://demo.nopcommerce.com/"
    And User Click on Login link
    And User enter his username and password
    And Click on Login button
    And Close Browser