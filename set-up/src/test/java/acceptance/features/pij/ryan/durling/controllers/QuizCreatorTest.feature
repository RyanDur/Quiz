Feature: The ability to create quiz's

  As a Quiz master
  I want to be able to make a quiz's
  So that a player can have fun.

  The set-up client program should enable a user who wishes to organise an electronic
  quiz game to create a new quiz (i.e., a set of questions) on the server, with a given name
  for the quiz, and a set of possible answers for each question. This will return a quiz game
  id.

  Background:
    Given a user has a quiz creator

  Scenario Outline: should be able to name a quiz
    When a user creates a quiz named <name>
    Then they should have a quiz with the name <name>

  Examples:
    | name  |
    | "foo" |
    | "bar" |

  Scenario Outline: should not be able to make a quiz without a name
    When a user creates a quiz without a <name>
    Then a quiz should not be created

  Examples:
    | name |
    | ""   |
    | " "  |

  Scenario: should not be able to make a quiz with null for a name
    When a user creates a quiz named null
    Then a quiz should not be created

  Scenario Outline: should be able to add a question to a quiz
    Given a user creates a quiz named <name>
    When a user adds a question like <question>
    Then the quiz should have that <question>

  Examples:
    | name  | question |
    | "foo" | "bar?"   |
    | "bar" | "foo?"   |

  Scenario Outline: should not be able to add an empty question
    Given a user creates a quiz named <name>
    When a user adds an empty question like <question>
    Then the question should not be added

  Examples:
    | name  | question |
    | "foo" | ""       |
    | "bar" | " "      |

  Scenario Outline: should not be able to add a null question
    Given a user creates a quiz named <name>
    Given a user tries to add a null question
    Then the question should not be added

  Examples:
    | name  |
    | "foo" |
    | "bar" |


  Scenario Outline: should be able to save it to the server
    Given a user creates a quiz named <name>
    When a user saves the quiz
    Then a user should be able to retrieve it

  Examples:
    | name  |
    | "foo" |
    | "bar" |

  Scenario: should not be able to save a null quiz
    When a user tries to save a null quiz
    Then the server should not be called