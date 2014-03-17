Feature: The ability to create quizzes

  As a Quiz master
  I want to be able to make a quiz
  So that a player can have fun.

  Background:
    Given a user has a quiz creator

  Scenario Outline: should be able to make a quiz with a name and get the quiz id
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
    And a user creates a question with <question> and <value>
    Then the question should be added

  Examples:
    | name  | question | value |
    | "foo" | "foo?"   | 7     |
    | "bar" | "bar?"   | 3     |

  Scenario Outline: should not be able to create an empty question
    When a user creates a quiz named <name>
    And a user creates a question with <question> and <value>
    Then the question should not be created

  Examples:
    | name  | question | value |
    | "foo" | ""       | 1     |
    | "bar" | " "      | 2     |
    | "baz" | "null"   | 3     |

  Scenario Outline: should not be able to create a question without a quiz
    And a user creates a question with <question> and <value>
    Then the question should not be created without a quiz

  Examples:
    | question | value |
    | "foo?"   | 7     |
    | "bar?"   | 3     |

  Scenario Outline: should not be able to add a question to a quiz with a value less than one
    When a user creates a quiz named <name>
    And a user creates a question with <question> and <value>
    Then the question should not be created

  Examples:
    | name  | question | value |
    | "foo" | "foo?"   | 0     |

  Scenario Outline: should be able to add an answer to a question
    When a user creates a quiz named <name>
    And a user creates a question with <question> and <value>
    And a user adds <answer> and mark if its <correct>
    Then the answer should be added

  Examples:
    | name  | question | value | answer | correct |
    | "foo" | "foo?"   | 7     | "baz"  | "true"  |
    | "bar" | "bar?"   | 3     | "foo"  | "false" |

  Scenario Outline: should not be able to add an answer without an answer
    When a user creates a quiz named <name>
    And a user creates a question with <question> and <value>
    And a user adds <answer> and mark if its <correct>
    Then the answer should not should be added

  Examples:
    | name  | question | value | answer | correct |
    | "foo" | "foo?"   | 7     | ""     | "true"  |
    | "bar" | "baz?"   | 3     | " "    | "false" |
    | "baz" | "bar?"   | 3     | "null" | "false" |


  Scenario Outline: should not be able to add an answer without a question
    When a user creates a quiz named <name>
    And a user adds <answer> and mark if its <correct>
    Then the answer should not should be added

  Examples:
    | name  | answer | correct |
    | "foo" | "bar"  | "true"  |
    | "bar" | "baz"  | "true"  |

  Scenario Outline: should be able to save it to the server
    When a user creates a quiz named <name>
    And is a valid quiz
    And a user saves the quiz
    Then the quiz should be saved

  Examples:
    | name  |
    | "foo" |
    | "bar" |

  Scenario Outline: should not be able to save an improperly named quiz
    When a user creates a quiz named <name>
    And is a valid quiz
    Then the quiz should not be saved

  Examples:
    | name   |
    | ""     |
    | " "    |
    | "null" |

  Scenario Outline: should not be able to save it to the server if the quiz is invalid
    When a user creates a quiz named <name>
    And is an invalid quiz
    And a user saves the quiz
    Then the quiz should not be saved

  Examples:
    | name  |
    | "foo" |
    | "bar" |

  Scenario: example
    Given I have this situation
    When something happens
    Then This should be the outcome