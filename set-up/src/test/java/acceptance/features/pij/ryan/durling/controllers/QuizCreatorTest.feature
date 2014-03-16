Feature: The ability to create quiz's

  As a Quiz master
  I want to be able to make a quiz
  So that a player can have fun.

  The set-up client program should enable a user who wishes to organise an electronic
  quiz game to create a new quiz (i.e., a set of questions) on the server, with a given name
  for the quiz, and a set of possible answers for each question. This will return a quiz game
  id.

  Background:
    Given a user has a quiz creator

  Scenario Outline: should be able to make a quiz with a name
    When a user creates a quiz named <name>
    Then they should have a quiz with the name <name>
    And return the quiz ID

  Examples:
    | name  |
    | "foo" |
    | "bar" |

  Scenario Outline: should not be able to make a quiz without a name
    When a user creates a quiz named <name>
    Then a quiz should not be created
    And not return the quiz ID

  Examples:
    | name   |
    | ""     |
    | " "    |
    | "null" |

  Scenario Outline: should be able to add a question to a quiz
    When a user creates a quiz named <name>
    And a user creates a question with <question>
    And adds the question to the quiz
    Then the question should be added

  Examples:
    | name  | question |
    | "foo" | "foo?"   |
    | "bar" | "bar?"   |

  Scenario Outline: should not be able to create an empty question
    When a user creates a quiz named <name>
    And a user creates a question with <question>
    Then the question should not be created

  Examples:
    | name  | question |
    | "foo" | ""       |
    | "bar" | " "      |
    | "baz" | "null"   |

  Scenario Outline: should be able to save it to the server
    When a user creates a quiz named <name>
    And a user saves the quiz
    Then the quiz should be saved

  Examples:
    | name  |
    | "foo" |
    | "bar" |

  Scenario Outline: should not be able to save an improperly named quiz
    When a user creates a quiz named <name>
    Then the quiz should not be saved

  Examples:
    | name   |
    | ""     |
    | " "    |
    | "null" |