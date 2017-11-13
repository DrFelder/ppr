Feature: Applying for an operation
  As a logged in user
  I want to be able to apply for an operation to fulfil a requirement

  Background:
    Given The testing database is used
    And I am on the homepage

  @skip
  Scenario: Apply for helper role
    Given I am signed in with username "user" and password "password"
    And I am on the operation detail page for operation "Delivering paper towels"
    Then I should see "Requirements"
    And I should see the "Driver" requirement
    When I click on "apply" at the "Driver" requirement
    And I enter "Is this a test question?" in the "Add a comment or question:" field
    And I click on "Send application"
    Then I should be on the operation detail page for operation "Delivering paper towels"
    And I should see "applied"

  @skip
  Scenario: Abort application for helper role
    Given I am signed in with username "user" and password "password"
    And I am on the operation detail page for operation "Delivering paper towels"
    Then I should see "Requirements"
    And I should see the "Driver" requirement
    When I click on "apply" at the "Driver" requirement
    And I click on "Abort application"
    Then I should be on the operation detail page for operation "Delivering paper towels"
    And I should not see "applied"

  @skip
  Scenario: The organizer can't apply for helper role
    Given I am signed in with username "user" and password "password"
    And I am on the operation detail page for operation "Delivering paper towels"
    Then I should see "Requirements"
    And I should see the "Driver" requirement
    Then I should not see "apply"
