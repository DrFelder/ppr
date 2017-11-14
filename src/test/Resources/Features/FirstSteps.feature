Feature: Cucumber example

  Scenario: Someone needs a success example
    Given this test is successful

  @skip
  Scenario: Someone needs a failure example that is skipped
    Given this test fails

  @skip
  Scenario: I want to access a website
    When I visit "https://google.com"

  Scenario: I want to look at the homepage
    When I visit the homepage
    Then the title should be "Example"