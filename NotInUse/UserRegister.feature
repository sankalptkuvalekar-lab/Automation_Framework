Feature: Register


  Scenario: Successful Register with Valid Credentials
    Given User Launch Chrome Browser
    When User open the URL "https://demo.nopcommerce.com/"
    And User Click on Register link
    And User enter his info
    And Click on Register button
    And click on Continue
    And Close Browser