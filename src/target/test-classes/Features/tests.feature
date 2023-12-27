Feature: Rami Levy Website Tests

  Background: Login
    Given Im on the Rami Levy Home Page
    When I click on open popup
    And I enter a valid 'waaed013@gmail.com' and 'test@@123' and click login button

  Scenario: Validate Successful Login
    Then my 'ואעד' should appear in the header

  Scenario: Add Item To Cart Via API And Validate Via UI
    When I Add Item With ID '377697' And Quantity '3' To The Cart Via API

  Scenario: Add item to cart and verify total sum
    When  I navigate to the Drinks category
    And add item to the cart by clicking the plus button
    Then I should observe that the total sum in the cart is displayed as '35.80 ₪'


