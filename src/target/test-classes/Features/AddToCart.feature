Feature: Add to Cart Test

  Scenario: Add item to cart and verify amount
    Given Im on the Rami Levy Home Page
    When I click to Drinks category
    And I click to plus button on item and add it to the cart
    Then verify the that item added to cart is '1'
