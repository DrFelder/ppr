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
    When I am on the homepage
    Then the title should be "Getting Started: Serving Web Content"
    When I visit "http://ppr.surreal.is:8080/list/"
    Then I should see "at,mollitia,libero"
