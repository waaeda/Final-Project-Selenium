Feature:shopping cart sum test
  Scenario: Add item to cart and verify total sum
    Given  Im on the Rami Levy Home Page
    When  I navigate to the Drinks category
    And add item to the cart by clicking the plus button
    Then I should observe that the total sum in the cart is displayed as '35.80 ₪'