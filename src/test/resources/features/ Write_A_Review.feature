Feature:  Write a Review Feature

  @review
  Scenario: User is able to write a review
    Given User opened the browser
    And User navigated to the application url and click on sign in button
    And User signed in
    When User click on write a review
    And User enter Title and Comment
    And User click on Send button
    Then Open a pop up box with message displayed Your comment has been added and will be available once approved by a moderator
    And User should be click on Ok
