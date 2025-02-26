Feature: User Management
  # This feature file is used to test the user management functionalities.

  Background:
    # Given steps to set up the initial state before each scenario.
    Given User is on the User List Table page
    And User clicked add user button

  @AddNewUser @sanity
  Scenario Outline: Add new user
    # When steps to capture user details and click the save button.
    When User capture first name as "<fname>", last name as "<lname>", username as "<uname>", password as "<pwd>", customer as "<customer>", role as "<role>", email as "<email>", cell as "<cell>"
    And User clicks the save button
    # Then step to verify that the user is added and appears in the user list table.
    Then User should be added and appear in the User List Table as first name as "<fname>", last name as "<lname>", customer as "<customer>", role as "<role>", email as "<email>", cell as "<cell>"

    Examples:
      # Examples table to provide different sets of data for the scenario outline.
      | fname  | lname  | uname | pwd   | customer   | role     | email            | cell   |
      | FName1 | LName1 | User1 | Pass1 | Company AAA| Admin    | admin@mail.com   | 082555 |
      | FName2 | LName2 | User2 | Pass2 | Company BBB| Customer | customer@mail.com | 083444 |