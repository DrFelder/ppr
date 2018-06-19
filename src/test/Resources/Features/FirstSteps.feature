Feature: First Steps

  @run
  Scenario: I want to look at the operation list
    When I am on the homepage
    Then the title should be "PPR - Home"
    When I visit "http://localhost:8081/#/operation/"
    Then I should see "PPR - Login"
    When I enter "user" in the field "username_login"
    And I enter "password" in the field "password_login"
    And I press the "submit_login" button
    And I wait for 3 seconds
    Then the title should be "PPR - Home"
    When I visit "http://localhost:8081/#/operation/"
    Then the title should be "PPR - Operation List"
    And I should see "delectus,aut,at"
    Then I click on link having id "logout_button"

    @run
  Scenario: I want to access the operation detail page
    When I visit "http://localhost:8081/#/operation/"
    Then I should see "PPR - Login"
    When I enter "user" in the field "username_login"
    And I enter "password" in the field "password_login"
    And I press the "submit_login" button
    And I wait for 3 seconds
    Then the title should be "PPR - Home"
    When I visit "http://localhost:8081/#/operation/"
    Then the title should be "PPR - Operation List"
    When I visit "http://localhost:8081/#/operation/1"
    And I wait for 3 seconds
    Then the title should be "PPR - Operation Details"
    And I should see "Aut quaerat non aut odit enim sunt est"
    And I should see "Equipment"
    And I should see "est,autem,possimus"
    And I should see "Eveniet optio eveniet dolor harum."
    And I should see "Helpers"
    And I should see "voluptas,quis,reprehenderit"
    And I should see "Ea ut qui quasi exercitationem et ut."
    Then I click on link having id "logout_button"

  Scenario: I want to access the organizer detail page
    When I visit "http://localhost:8081/#/operation/"
    Then I should see "PPR - Login"
    When I enter "user" in the field "username_login"
    And I enter "password" in the field "password_login"
    And I press the "submit_login" button
    And I wait for 3 seconds
    Then the title should be "PPR - Home"
    When I visit "http://localhost:8081/#/operation/1"
    And I wait for 3 seconds
    And I click on link having text "collinsbrisa"
    Then the title should be "PPR - User Details"
    Then I should see "1973-05-24"
    Then I click on link having id "logout_button"
