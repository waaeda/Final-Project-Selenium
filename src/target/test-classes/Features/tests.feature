Feature: Rami Levy Website Tests

  Background: Login
    Given Im on the Rami Levy Home Page
#    When I click on open popup
#    And I enter a valid 'kamilabuelhija@gmail.com' and '12345678' and click login button

  Scenario: Validate Successful Login
    Then my 'kamil' should appear in the header

  Scenario: Add Item To Cart Via API And Validate Via UI
    When I Add Item With ID '377697' And Quantity '3' To The Cart Via API

  Scenario: Add item to cart and verify total sum
    When I click to Drinks category
    And I click to plus button on item and add it to the cart
    Then I should see the total sum in the cart is '35.8 ₪'

  Scenario: Add item to cart and verify amount
    When I click to Drinks category
    And I click to plus button on item and add it to the cart
    Then I should see the total amount of items is '1'


  Scenario: Search about specific type of drinks
    When I click to Drinks category
    And I click on filter button and choose schweppes drinks
    Then verify i got a schweppes drink

  Scenario: input milk in the search input
    When I type milk in search input and click enter
    Then milk products must showed




