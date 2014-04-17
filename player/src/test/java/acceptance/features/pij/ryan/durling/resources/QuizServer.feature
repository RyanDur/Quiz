Feature: Connect to the server

  Background:
    Given there is a link to the server

  Scenario: Should be able to get the qiz names from the server
    When a user asks for the available quizzes
    Then a user should receive a list of quizzes available

  Scenario Outline: Should be able to get a quiz
    When a user asks for a quiz by <id>
    Then a user should receive a quiz

  Examples:
    | id |
    | 4  |
    | 5  |

  Scenario: Should be able to check the high score of a quiz
    When a user submits a quiz
    Then it should check the high score