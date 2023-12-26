Feature: Rami Levy Website Tests Without Login

  Background:
    Given Im on the Rami Levy Home Page

  Scenario: Validate Store Search Engine Working As Expected - Test By Waaed
    When I search for Product By Name 'גבינת שמנת טבעי'
    Then The Product 'גבינת שמנת טבעי' Should Be The First Item At The Search Page

  Scenario: Validate Store Voice Search Engine Working As Expected - Test By Waaed
    When I Click On Search By Voice
    And I Play Audio File
    And I Should Be Redirected To The Search Page
    Then The Product 'גבינת שמנת טבעי' Should Be The First Item At The Search Page

  Scenario: Add item to cart and verify total sum
    When I click to Drinks category
    And I click to plus button on item and add it to the cart
    Then I should see the total sum in the cart is '35.80 ₪'