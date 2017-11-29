Feature: First Steps
  Scenario: I want to look at the operation list
    When I am on the homepage
    Then the title should be "Project Puerto Rico - Homepage"
    When I visit "http://ppr.surreal.is:8080/list/"
    Then I should see "Please log in:"
    When I enter "user" in the field "username_login"
    And I enter "password" in the field "password_login"
    And I press the "submit_login" button
    Then the title should be "Project Puerto Rico - Operation List"
    And I should see "at,mollitia,libero"

  Scenario: I want to access the operation detail page
    When I visit "http://ppr.surreal.is:8080/list/"
    Then I should see "Please log in:"
    When I enter "user" in the field "username_login"
    And I enter "password" in the field "password_login"
    And I press the "submit_login" button
    Then the title should be "Project Puerto Rico - Operation List"
    When I click on link having text "details"
    Then the title should be "Detail page for delectus,aut,at"
    And I should see "Aut quaerat non aut odit enim sunt est"

  Scenario: I want to access the organizer detail page
    When I visit "http://ppr.surreal.is:8080/list/"
    Then I should see "Please log in:"
    When I enter "user" in the field "username_login"
    And I enter "password" in the field "password_login"
    And I press the "submit_login" button
    Then the title should be "Project Puerto Rico - Operation List"
    When I click on link having text "details"
    And I click on link having text "Kennedi Lowe"
    Then the title should be "Detail page for Kennedi Lowe"
    Then I should see "+00(8)9096522708"
