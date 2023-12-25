Feature: Login Page

  Scenario: Validate Successful Login
    Given  Im on the Rami Levy Home Page
    When  I click on open popup
    And  I enter a valid 'waaed013@gmail.com' and 'test@@123' and click login button
    Then  my 'ואעד' should appear in the header