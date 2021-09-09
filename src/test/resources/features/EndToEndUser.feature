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
      Given User signed in
      And User able to proceed add to cart and displayed pop up box
      When User click on Proceed to Checkout
      Then Check the User sees the product name and Availability as Instock
      And  Check unit Price equal to what was captured previously and Quantity to what was set earlier
      And Check the Total is equal to twice the amount with added charges for shipping
      And Click on Proceed to Check out again and reach till payment and click on Terms and condition check box
      And On Payment Page click on Pay by bank wire and Click on I confirm my Order
      And Check the order submit page and message Your order on My Store is complete. also check is amount is right






