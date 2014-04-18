Feature: create quizzes and its elements

  Background:
    Given there is a quiz factory


  Scenario Outline: create a quiz with an id
    When a factory creates a quiz with <id> and <title>
    Then it should return a quiz with <id>

  Examples:
    | id   | title |
    | 3    | "foo" |
    | 45   | "bar" |
    | 5009 | "baz" |


  Scenario Outline: create a question
    When a factory creates a <question> with a <score>
    Then it should return a <question> with a <score>

  Examples:
    | question | score |
    | "what?"  | 4     |


  Scenario Outline: create a question
    When a factory creates an <answer> with a <value>
    Then it should return an <answer> with a <value>

  Examples:
    | answer | value   |
    | "yes"  | "true"  |
    | "no"   | "false" |