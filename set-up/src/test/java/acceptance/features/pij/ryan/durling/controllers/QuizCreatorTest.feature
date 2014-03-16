Feature: The ability to create quiz's

  As a Quiz master
  I want to be able to make a quiz's
  So that a player can have fun.

  The set-up client program should enable a user who wishes to organise an electronic
  quiz game to create a new quiz (i.e., a set of questions) on the server, with a given name
  for the quiz, and a set of possible answers for each question. This will return a quiz game
  id.

  Background:
    Given I have a quiz creator

  Scenario Outline: should be able to name a quiz
    When I create a quiz named <name>
    Then I should have a quiz with the name <name>

  Examples:
    | name  |
    | "foo" |
    | "bar" |

  Scenario Outline: should not be able to make a quiz without a name
    Given I create a quiz without a <name>

  Examples:
    | name |
    | ""   |
    | " "  |

  Scenario: should not be able to make a quiz with null for a name
    Given I create a quiz named null


  Scenario Outline: should be able to add a question to a quiz
    Given I create a quiz named <name>
    When I add a question like <question>
    Then the quiz should have that <question>

  Examples:
    | name  | question |
    | "foo" | "bar?"   |
    | "bar" | "foo?"   |