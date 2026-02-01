Feature: Login Feature

Scenario Outline: Login user with Incorrect EmaiL and  Passowrd
Given User Launch Chrome Browser
When User open the URL "https://automationexercise.com/"
  Then Verify that home page is visible successfully
And User Click on the Login link
  Then Verify Login to your account is visible
And User enter his "<Email>" and "<password>"
And User Click on Login button
  Then  Verify error Your email or password is incorrect! is visible
  And Close Browser

Examples:

| Email | password |
| admin@yourstore1.com | admin123 |
