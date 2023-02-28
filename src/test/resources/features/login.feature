Feature: Login functionality to hudl website

  Scenario Outline: Login to hudl page
    Given User navigates to hudl website
    And User clicks on LogIn button
    And User should see signIn button
    And User enters valid "<email>", password
    Then Login is success with "<response>"
    Examples:
      | email                    | response |
      | monica.marneni@gmail.com | VALID    |
      | test@gmail.com           | INVALID  |
      | test                     | INVALID  |
      |                          | INVALID  |


