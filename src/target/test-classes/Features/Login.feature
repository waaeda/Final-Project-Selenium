Feature: Login Page

  Scenario: Validate Successful Login
    Given Im on the Rami Levy Home Page
    When I click on open popup
    And I enter a valid 'kamilabuelhija@gmail.com' and '12345678' and click login button
    Then my 'kamil' should appear in the header