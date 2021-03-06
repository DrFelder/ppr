Feature: create operation
  As a signed in user
  i want to create a new operation
  and provide additional information regarding my intentions
  in order to find willing helpers.

  Background:
    Given I am on the homepage

  Scenario: Open new operation dialog
    When I visit "http://localhost:8081/#/operation"
    Then I should see "Login"
    When I enter "user" in the field "username_login"
    And I enter "password" in the field "password_login"
    And I press the "submit_login" button
    And I wait for 3 seconds
    When I click on link having text "create operation"
    Then I am on the "http://localhost:8081/#/operation/create" page
    Then I click on link having id "logout_button"

  Scenario: Enter valid data and save the operation
    When I visit "http://localhost:8081/#/operation"
    Then I should see "Login"
    When I enter "user" in the field "username_login"
    And I enter "password" in the field "password_login"
    And I press the "submit_login" button
    And I wait for 3 seconds
    When I click on link having text "create operation"
    When I enter "operation XY" in the field "title"
    And I enter "Karlsruhe" in the field "location"
    And I enter "01.01.2018" in the field "date"
    And I enter "public description" in the field "publicdescription"
    And I enter "private description" in the field "privatedescription"
    And I press the "submit" button
    And I wait for 3 seconds
    Then I am on the "http://localhost:8081/#/operation/103" page
    And I should see "operation XY"
    Then I click on link having id "logout_button"

  Scenario: Enter invalid data and save the operation
    When I visit "http://localhost:8081/#/operation/"
    Then I should see "Login"
    When I enter "user" in the field "username_login"
    And I enter "password" in the field "password_login"
    And I press the "submit_login" button
    And I wait for 3 seconds
    When I click on link having text "create operation"
    When I enter "operation XY" in the field "title"
    And I enter "Karlsruhe" in the field "location"
    And I enter "" in the field "date"
    And I enter "" in the field "publicdescription"
    And I enter "" in the field "privatedescription"
    And I press the "submit" button
    Then I am on the "http://localhost:8081/#/operation/create" page
    Then I click on link having id "logout_button"
