Feature: Shopping Cart Test

  Scenario: Add item to cart and verify total sum
    Given I am on the Rami Levy website
    When I click to Drinks category
    And I click to plus button on item and add it to the cart
    Then I should see the total sum in the cart is '35.80 ₪'
