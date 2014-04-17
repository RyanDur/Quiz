Feature: A menu option to pick a quiz

  Scenario Outline: should have an option for a quiz
    Given a user has an option with a <title> and an <id>
    Then it should have the <title> and <id>

  Examples:
    | title | id |
    | "foo" | 4  |
    | "bar" | 79 |