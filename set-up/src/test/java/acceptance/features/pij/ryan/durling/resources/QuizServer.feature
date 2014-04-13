Feature: Ability to communicate with the server

  Background:
    Given a user has a Quiz Server

  Scenario Outline: Should be able to get a new quiz
    When a user creates a quiz the the <name>
    Then the server should return a quiz

  Examples:
    | name  |
    | "foo" |
    | "bar" |

  Scenario Outline: Should be able to create a question
    When a user creates a question <question> with a score of <score>
    Then the server should return a question

  Examples:
    | question | score |
    | "foo"    | 7     |
    | "bar"    | 5     |


  Scenario Outline: Should be able to save a quiz
    When a user creates a quiz the the <name>
    Then a user can save a quiz

  Examples:
    | name  |
    | "foo" |
    | "bar" |

  Scenario Outline: Should be able to create an answer
    When a user create an answer <answer> with a <value>
    Then the user should have an answer

  Examples:
    | answer | value   |
    | "foo"  | "true"  |
    | "bar"  | "false" |

  Scenario Outline: Should be able to lock a quiz
    Then a user can lock a quiz with an id of <id>

  Examples:
    | id |
    | 3  |
    | 4  |

  Scenario Outline: Should be able to unlock a quiz
    Then a user can unlock a quiz with an id of <id>

  Examples:
    | id |
    | 3  |
    | 4  |