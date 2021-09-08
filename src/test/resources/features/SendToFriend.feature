Feature: Send To Friend Feature

  @send_friend
  Scenario: User is able to send product details to a friend
    Given User opened the browser
    And User navigated to the application url and click on sign in button
    When User select the category as T-Shirts and navigate to T-shirts page
    And User click on the product: Faded Short Sleeve T-shirts
    And User click on Send to a Friend Link, fill the details and click on Send
    Then Message appeared that Email sent in a pop up








