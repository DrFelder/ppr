Feature: Applying for an operation
  As a logged in user
  I want to be able to apply for an operation to fulfil a requirement

  Background:
    And I am on the homepage

  Scenario: Requirements can be seen
    When I visit "http://ppr.surreal.is:8080/list/"
    Then I should see "Login"
    When I enter "user" in the field "username_login"
    And I enter "password" in the field "password_login"
    And I press the "submit_login" button
    And I visit "http://ppr.surreal.is:8080/detail?id=1"
    Then I should see "Equipment:"
    And I should see "est,autem,possimus"
    Then I should see "Helpers:"
    And I should see "voluptas,quis,reprehenderit"

  Scenario: Apply for requirement and helper role
    When I visit "http://ppr.surreal.is:8080/list/"
    Then I should see "Login"
    When I enter "user" in the field "username_login"
    And I enter "password" in the field "password_login"
    And I press the "submit_login" button
    And I visit "http://ppr.surreal.is:8080/detail?id=10"
    When I click on link having text "Apply"
    Then I am on the "http://ppr.surreal.is:8080/applyforequipment" page
    When I press the "submit" button
    Then I am on the "http://ppr.surreal.is:8080/detail" page
    And I should see "Applied"
    When I click on link having text "Apply"
    Then I am on the "http://ppr.surreal.is:8080/applyforhelper" page
    When I press the "submit" button
    Then I am on the "http://ppr.surreal.is:8080/detail" page

  Scenario: See application status
    When I visit "http://ppr.surreal.is:8080/list/"
    Then I should see "Login"
    When I enter "user" in the field "username_login"
    And I enter "password" in the field "password_login"
    And I press the "submit_login" button
    And I visit "http://ppr.surreal.is:8080/detail?id=2"
    Then I should see "Applied"
    When I visit "http://ppr.surreal.is:8080/detail?id=1"
    Then I should see "Declined"
    Then I should see "Accepted"

