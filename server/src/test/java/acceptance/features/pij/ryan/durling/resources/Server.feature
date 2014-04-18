Feature: server interface for the clients

  Background:
    Given there is a server


  Scenario Outline: Should be able to create a quiz
    When a user creates a quiz named <title>
    Then a user receives a quiz

  Examples:
    | title |
    | "foo" |
    | "bar" |


  Scenario Outline: Should be able to create a question
    When a user creates a <question> with a <score>
    Then the user should receive a question

  Examples:
    | question | score |
    | "foo?"   | 5     |
    | "bar?"   | 7     |


  Scenario Outline: Should be able to create a question
    When a user creates a <correct> <answer>
    Then the user should receive an answer

  Examples:
    | correct | answer  |
    | "true"  | "flop"  |
    | "false" | "bloop" |


  Scenario Outline: Should be able to save a quiz
    When a user creates a quiz named <title>
    Then the user can save a quiz

  Examples:
    | title |
    | "foo" |
    | "bar" |


  Scenario: should be able to get a set of available quizzes
    When a user asks for the available quizzes
    Then a user receives a list of available quizzes


  Scenario Outline: should be able to get a quiz by id
    When a user asks for quiz <id>
    Then a user receives a quiz

  Examples:
    | id |
    | 34 |
    | 5  |


  Scenario Outline: Should be able to check the players score against the high score of a given quiz
    When a user creates a quiz named <title>
    And a user checks if he has the high <score>
    Then the user receives a notification

  Examples:
    | title | score |
    | "foo" | 45    |
    | "bar" | 3     |


  Scenario Outline: Should be able to set the players score against the high score of a given quiz
    When a user creates a quiz named <title>
    Then a user set the high <score>

  Examples:
    | title | score |
    | "foo" | 45    |
    | "bar" | 3     |