@login

Feature: Rami levy Website- Login Page

  Scenario Outline: Validate - Successful & Unsuccessful Login
    Given I access the Rami levy login page
    When I enter a username <email>
    And I enter a password <password>
    And I click on the submit button
    Then I should be presented with the following login validation <loginValidation>

    Examples:
      | email  | password     | loginValidation |
      | waaed013@gmail.com | test@@12 | validation succeeded   |
      | adim@gmail.com | adim123   | validation failed      |
      | test@gmail.com | testest12    | validation failed      |

