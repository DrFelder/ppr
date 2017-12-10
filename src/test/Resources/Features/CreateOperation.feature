Feature: create operation

  As a signed in user
  i want to create a new operation
  and provide additional information regarding my intentions
  in order to find willing helpers.

  Background:
    Given I am on the homepage

  @skip
  Scenario: Open new operation dialog
    Given I am signed in with username "USER" and password "PASSWORD"
    And I am on the "main" page
    When I press the "new operation" button
    Then I am on the "new operation" page

  @skip
  Scenario: Enter valid data and save the operation
    When I visit "http://ppr.surreal.is:8080/list/"
    Then I should see "Please log in:"
    When I enter "user" in the field "username_login"
    And I enter "password" in the field "password_login"
    And I press the "submit_login" button
    When I visit "http://ppr.surreal.is:8080/createoperation/"
    When I enter "operation XY" in the field "title"
    And I enter "Karlsruhe" in the field "location"
    And I enter "01.01.2018" in the field "date"
    And I enter "public description" in the field "publicdescription"
    And I enter "private description" in the field "privatedescription"
    And I press the "submit" button
    Then I am on the "details" page
    And I should see "operation XY"

  @skip
  Scenario: Enter invalid data and save the operation
    Given I am signed in with username "USER" and password "PASSWORD"
    And I am on the "new operation" page
    When I enter "operation XY" in the field "title"
    And I enter "Karlsruhe" in the field "location"
    And I enter "no date" in the field "date"
    And I enter "" in the field "public_descripion"
    And I enter "" in the field "private_description"
    And I press the "save" button
    Then I am on the "new operation" page
    And I receive a "error" message
