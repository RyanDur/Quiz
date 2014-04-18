Feature: create a quiz option

  Scenario Outline: create a quiz option
    Given there is a quiz option factory
    When a user creates an option with <title> and <id>
    Then there should be a quiz option

  Examples:
    | title | id |
    | "foo" | 3  |