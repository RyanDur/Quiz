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
    And throw an IllegalArgumentException

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
    And throw an IllegalArgumentException

  Examples:
    | name  | question | value |
    | "foo" | ""       | 1     |
    | "bar" | " "      | 2     |
    | "baz" | "null"   | 3     |

  Scenario Outline: should not be able to create a question without a quiz
    And a user creates a question with <question> and <value>
    Then the question should not be created
    And throw an IllegalQuizCreationException

  Examples:
    | question | value |
    | "foo?"   | 7     |
    | "bar?"   | 3     |

  Scenario Outline: should not be able to add a question to a quiz with a value less than one
    When a user creates a quiz named <name>
    And a user creates a question with <question> and <value>
    Then the question should not be created
    And throw an IllegalArgumentException

  Examples:
    | name  | question | value |
    | "foo" | "foo?"   | 0     |
    | "foo" | "foo?"   | -1    |
    | "foo" | "foo?"   | -2    |

  Scenario Outline: should be able to add an answer to a question
    When a user creates a quiz named <name>
    And a user creates a question with <question> and <score>
    And a user adds <answer> and mark if its <correct>
    Then the answer should be added

  Examples:
    | name  | question | score | answer | correct |
    | "foo" | "foo?"   | 7     | "baz"  | "true"  |
    | "bar" | "bar?"   | 3     | "foo"  | "false" |

  Scenario Outline: should not be able to add an answer without an answer
    When a user creates a quiz named <name>
    And a user creates a question with <question> and <value>
    And a user adds <answer> and mark if its <correct>
    Then the answer should not be added
    And throw an IllegalArgumentException

  Examples:
    | name  | question | value | answer | correct |
    | "foo" | "foo?"   | 7     | ""     | "true"  |
    | "bar" | "baz?"   | 3     | " "    | "false" |
    | "baz" | "bar?"   | 3     | "null" | "false" |

  Scenario Outline: should not be able to add an answer without a question
    When a user creates a quiz named <name>
    And a user adds <answer> and mark if its <correct>
    Then the answer should not be added
    And throw an IllegalArgumentException

  Examples:
    | name  | answer | correct |
    | "foo" | "bar"  | "true"  |
    | "bar" | "baz"  | "true"  |

  Scenario Outline: ahould not be able to add a question without a quiz
    When a user adds a question that is not apart of the quiz
    And a user adds <answer> and mark if its <correct>
    Then the answer should not be added
    And throw an IllegalQuizCreationException

  Examples:
    | answer | correct |
    | "bar"  | "true"  |
    | "baz"  | "true"  |

  Scenario Outline: should not be able to add an answer to a question that is not in the quiz
    When a user creates a quiz named <name>
    And a user adds a question that is not apart of the quiz
    And a user adds <answer> and mark if its <correct>
    Then the answer should not be added
    And throw an IllegalArgumentException

  Examples:
    | name  | answer | correct |
    | "foo" | "bar"  | "true"  |
    | "bar" | "baz"  | "true"  |

  Scenario Outline: should be able to save it to the server
    When a user creates a quiz named <name>
    And the quiz is <valid>
    And a user saves the quiz
    Then the quiz should be saved

  Examples:
    | name  | valid  |
    | "foo" | "true" |
    | "bar" | "true" |

  Scenario Outline: should not be able to save an improperly named quiz
    When a user creates a quiz named <name>
    And the quiz is <valid>
    And a user saves the quiz
    Then the quiz should not be saved
    And throw an IllegalQuizCreationException

  Examples:
    | name   | valid  |
    | ""     | "true" |
    | " "    | "true" |
    | "null" | "true" |

  Scenario Outline: should not be able to save it to the server if the quiz is invalid
    When a user creates a quiz named <name>
    And the quiz is <valid>
    And a user saves the quiz
    Then the quiz should not be saved
    And throw an InvalidQuizException

  Examples:
    | name  | valid   |
    | "foo" | "false" |
    | "bar" | "false" |

  Scenario: should not be able to save a quiz that does not exist
    When a user saves the quiz
    Then the quiz should not be saved
    And throw an IllegalQuizCreationException