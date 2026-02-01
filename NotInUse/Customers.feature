
 Feature: Customer

  Background: Below are the common steps for each scenario
   Given User Launch Chrome Browser
   When User open URL "https://admin-demo.nopcommerce.com/login?returnUrl=%2Fadmin%2F"
   And User enters Email as "admin@yourstore.com" and Password as "admin"
   And click on Login
   Then User can view Dashboard

@sanity
Scenario: Add a new Customer
  When User click on customers Menu
And click on customers Menu Item
And click on Add new button
Then User can view Add new customer page
When User enter customer info
And click on Save button
Then User can view confirmation message "The new customer has been added successfully."
 And Close Browser


 @regression
  Scenario: Search Customer by EmailID
    When User click on customers Menu
  And click on customers Menu Item
  And Enter customer Email
  When Click on Search button
  Then User should found Email in the Search table
  And Close Browser


 @regression
  Scenario: Search Customer By Name
    When User click on customers Menu
    And click on customers Menu Item
    And Enter customer FirstName
    And Enter customer LastName
    When Click on Search button
    Then User should found Name in the Search table
    And Close Browser
