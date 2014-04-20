Feature: Orchestrate the making of a quiz

  Background:
    Given there is a quiz maker

  Scenario Outline: Should be able to create a quiz
    When a user creates a quiz <title>
    Then a quiz is made

  Examples:
    | title |
    | "foo" |
    | "bar" |

  Scenario Outline: should be able to add a question to a quiz
    Given a user creates a quiz <title>
    When a user adds a <question> with a <score>
    Then a question should be added to the quiz

  Examples:
    | title | question | score |
    | "foo" | "Whaaa?" | 7     |
    | "bar" | "noooo?" | 78    |


  Scenario Outline: should be able to add an answer to a question
    Given a user creates a quiz <title>
    When a user adds a <question> with a <score>
    And a user adds an <answer> with a <value>
    Then a answer should be added to the question

  Examples:
    | title | question | score | answer  | value   |
    | "foo" | "Whaaa?" | 7     | "yep"   | "true"  |
    | "bar" | "noooo?" | 78    | "forty" | "false" |


  Scenario Outline: should be able to save a quiz
    Given a user creates a quiz <title>
    Then a user can save a quiz

  Examples:
    | title |
    | "foo" |
    | "bar" |

  Scenario Outline: should be able to check if a quiz is valid
    Given a user creates a quiz <title>
    Then a user can validate the quiz

  Examples:
    | title |
    | "foo" |
    | "bar" |

  Scenario Outline: should be able to check if the quiz exists
    Given a user creates a quiz <title>
    Then a user can check if the quiz is <empty>

  Examples:
    | title | empty   |
    | "foo" | "false" |
    | "bar" | "false" |

  Scenario Outline: should be able to check if the quiz exists
    Then a user can check if the quiz is <empty>

  Examples:
    | empty  |
    | "true" |
    | "true" |


  Scenario: should get the list of available quizzes
    When a user asks for the available quizzes
    Then a user should receive the available quizzes

  Scenario: should get the list of closed quizzes
    When a user asks for the closed quizzes
    Then a user should receive the closed quizzes

  Scenario: should be able to close a quiz
    When a user closes quiz 7
    Then quiz 7 should be closed

  Scenario: should be able to open a quiz
    When a user opens quiz 7
    Then quiz 7 should be opened

  Scenario Outline: should be able to add a question to a quiz
    Given a user creates a quiz <title>
    When a user adds a <question> with a <score>
    And a user asks if a question is valid
    Then it should check if it is valid

  Examples:
    | title | question | score |
    | "foo" | "Whaaa?" | 7     |
    | "bar" | "noooo?" | 78    |