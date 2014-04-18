Feature: Should be able to store high scores

  Background:
    Given there is a high score controller

  Scenario Outline: should have the high score if none exists
    When a user can check if there <score> beats the <current high score>
    Then a user receives the <result>

  Examples:
    | score | current high score | result |
    | 73    | 0                  | "true" |
    | 5     | 0                  | "true" |


  Scenario Outline: should be able to set the high score
    When a <user> has a quiz <id>
    Then a user sets there <score>
    When a user can check if there <score> beats the <current high score>
    Then a user receives the <result>

  Examples:
    | user    | score | id   | current high score | result  |
    | "Ryan"  | 45    | 3    | 0                  | "true"  |
    | "Keimi" | 67    | 1005 | 93                 | "false" |