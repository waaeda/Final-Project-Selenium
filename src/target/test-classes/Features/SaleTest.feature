Feature: Rami Levy Website Tests
  Scenario: Validating Sale Discount
    Given  Im on the Rami Levy Home Page
    When I click on the Sale button in the navbar and then sale only button
    And I select a product under the sale section
    Then I validate that the new price reflects a 20% discount and the Origin price is 21.80