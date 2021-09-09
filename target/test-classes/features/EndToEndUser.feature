Feature: End To End User Feature

  @end_to_end_user
  Scenario: User able to done End to end user journey
    Given User signed in
    When User click on plus button to increase the quantity of product
    And User select large size of the product
    And User click on Add to cart button
    Then Message displayed Product successfully added to your shopping cart
    And Check the Quantity and Color
    And Check Total Price is twice the amount fetched earlier.

    @checkout
    Scenario: User able to done checkout process of product
      Given



