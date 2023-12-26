Feature: Rami Levy Website Tests Without Login

  Background:
    Given Im on the Rami Levy Home Page

  Scenario: Validate Store Search Engine Working As Expected - Test By Waaed
    When I search for Product By Name 'אושן ספריי חמוציות'
    Then The Product 'אושן ספריי חמוציות' Should Be The First Item At The Search Page

  Scenario: Validate Store Voice Search Engine Working As Expected - Test By Waaed
    When I Click On Search By Voice
    And I Play Audio File
    And I Should Be Redirected To The Search Page
    Then The Product 'פריגת סחוט תפוזים' Should Be The First Item At The Search Page

  Scenario: Add item to cart and verify total sum - Test By Adim
    When I click to Drinks category
    And I click to plus button on item and add it to the cart
    Then I should see the total sum in the cart is '35.8 ₪'

  Scenario: Add item to cart and verify amount - Test By Kamil
    When I click to Drinks category
    And I click to plus button on item and add it to the cart
    Then I should see the total amount of items is '1'

  Scenario: Search about specific type of drinks - Test By Kamil
    When I click to Drinks category
    And I click on filter button and choose schweppes drinks
    Then verify i got a schweppes drink

  Scenario: input milk in the search input - Test By Kamil
    When I type milk in search input and click enter
    Then milk products must showed