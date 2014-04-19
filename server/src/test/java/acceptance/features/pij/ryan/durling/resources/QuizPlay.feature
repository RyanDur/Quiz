Feature: Orchestrate the playing of a quiz

  Background:
    Given there is a Quiz play object


  Scenario: should be able to get a list of available quizzes
    When a user requests a set of available quizzes
    Then a user should receive a list of available quizzes


  Scenario Outline: should be able to get a quiz
    When a user request a <quiz>
    Then a user should receive a quiz

  Examples:
    | quiz |
    | 3    |
    | 4    |


  Scenario Outline: should be able to get a high score
    When a user request a high score for a <quiz>
    Then a user should receive a high score

  Examples:
    | quiz |
    | 3    |
    | 4    |


  Scenario Outline: should be able to set a high score
    When a <user> sets a high <score> for a <quiz>
    Then a user should set the high score

  Examples:
    | user    | score | quiz |
    | "ryan"  | 56    | 73   |
    | "keimi" | 2     | 72   |


