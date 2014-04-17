Feature: Quiz for a user to play

  Scenario Outline: should be able to make a quiz
    Given a user has a quiz named <title> and an <id>
    Then it should have a <title> and an <id>

  Examples:
    | title  | id |
    | "bluf" | 3  |
    | "fart" | 67 |

  Scenario Outline: should be able to add a question to a quiz
    Given a user has a quiz named <title> and an <id>
    When a user adds a question
    Then the quiz should have the question

  Examples:
    | title  | id |
    | "bluf" | 3  |
    | "fart" | 67 |


  Scenario Outline: should not be a valid without a question
    Given a user has a quiz named <title> and an <id>
    Then the quiz should be <valid>

  Examples:
    | title  | id | valid   |
    | "bluf" | 3  | "false" |
    | "fart" | 67 | "false" |


  Scenario Outline: should be able to add an answer to a question
    Given a user has a quiz named <title> and an <id>
    When a user adds a question
    Then the quiz should be <valid>

  Examples:
    | title  | id | valid  |
    | "bluf" | 3  | "true" |
    | "fart" | 67 | "true" |
