Feature: server interface for the clients

  Background:
    Given there is a server

  Scenario Outline: Should be able to create a quiz
    When a user creates a quiz named <title>
    Then the user should receive a quiz

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