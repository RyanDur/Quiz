Feature: Quiz for a user to play

  Scenario Outline: should be able to make a quiz
    Given a user has a quiz named <title> and an <id>
    Then it should have a <title> and an <id>

  Examples:
    | title  | id |
    | "bluf" | 3  |
    | "fart" | 67 |