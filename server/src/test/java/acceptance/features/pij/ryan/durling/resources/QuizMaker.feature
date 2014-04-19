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


  Scenario Outline: should be able to get the name of the quiz
    Given a user creates a quiz <title>
    Then a user can get the name of the quiz

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