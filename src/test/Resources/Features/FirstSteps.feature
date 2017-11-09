Feature: Cucumber example

  Scenario: Someone needs a success example
    Given this test is successful

  @skip
  Scenario: Someone needs a failure example that is skipped
    And this test fails


  Scenario: I want to access a website
    When I visit "https://google.com"
