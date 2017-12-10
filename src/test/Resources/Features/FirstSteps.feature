Feature: First Steps
  Scenario: I want to look at the operation list
    When I am on the homepage
    Then the title should be "PPR - Home"
    When I visit "http://ppr.surreal.is:8080/list/"
    Then I should see "Please log in:"
    When I enter "user" in the field "username_login"
    And I enter "password" in the field "password_login"
    And I press the "submit_login" button
    Then the title should be "PPR - Operation List"
    And I should see "at,mollitia,libero"

  Scenario: I want to access the operation detail page
    When I visit "http://ppr.surreal.is:8080/list/"
    Then I should see "Please log in:"
    When I enter "user" in the field "username_login"
    And I enter "password" in the field "password_login"
    And I press the "submit_login" button
    Then the title should be "PPR - Operation List"
    When I click on link having text "details"
    Then the title should be "PPR - Detail page for delectus,aut,at"
    And I should see "Aut quaerat non aut odit enim sunt est"
    And I should see "Equipment:"
    And I should see "est,autem,possimus"
    And I should see "Eveniet optio eveniet dolor harum."
    And I should see "Helpers:"
    And I should see "voluptas,quis,reprehenderit"
    And I should see "Ea ut qui quasi exercitationem et ut."

  Scenario: I want to access the organizer detail page
    When I visit "http://ppr.surreal.is:8080/list/"
    Then I should see "Please log in:"
    When I enter "user" in the field "username_login"
    And I enter "password" in the field "password_login"
    And I press the "submit_login" button
    Then the title should be "PPR - Operation List"
    When I click on link having text "details"
    And I click on link having text "Kennedi Lowe"
    Then the title should be "PPR - Detail page for Kennedi Lowe"
    Then I should see "1973-05-24"
