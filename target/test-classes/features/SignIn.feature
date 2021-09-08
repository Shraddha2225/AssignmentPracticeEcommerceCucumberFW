Feature: Sign In Feature

  @sign_in
  Scenario: User is able to signing in the application
    Given User opened the browser
    And User navigated to the application url and click on sign in button
    When User enter Email "icici5@gmail.com" and password as "nandu2225"
    And User click on sign in button
    Then User navigate to My Account page
