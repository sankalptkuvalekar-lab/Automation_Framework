Feature: Login

  @sanity
  Scenario: Successful Login with Valid Credentials
    Given User Launch Chrome Browser
    When User open URL "https://admin-demo.nopcommerce.com/login?returnUrl=%2Fadmin%2F"
    And User enters Email as "admin@yourstore.com" and Password as "admin"
    And click on Login
    Then Page Title should be "Dashboard / nopCommerce administration"
    When  user click on Logout link

    And Close Browser

    @regression
  Scenario Outline: Login Data Driven
    Given User Launch Chrome Browser
    When User open URL "https://admin-demo.nopcommerce.com/login"
    And User enters Email as "<email>" and Password as "<password>"
    And click on Login
    Then Page Title should be "Dashboard / nopCommerce administration"
    When  user click on Logout link

    And Close Browser


    Examples:

      | email | password |
      | admin@yourstore.com | admin |
      | admin@yourstore1.com | admin123 |


