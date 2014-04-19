Feature: Should be able to store high scores

  Background:
    Given there is a high score controller

  Scenario Outline: should be able to get the high score
    When a <user> has a quiz <id>
    And a user sets there <score>
    And a user gets the score via the quiz <id>
    Then a user should receive the score

  Examples:
    | user    | score | id   |
    | "Ryan"  | 45    | 3    |
    | "Keimi" | 67    | 1005 |


  Scenario Outline: should be able to set the high score
    When a <user> has a quiz <id>
    Then a user sets there <score>

  Examples:
    | user    | score | id   |
    | "Ryan"  | 45    | 3    |
    | "Keimi" | 67    | 1005 |