Feature: Coordinate the playing of a quiz

  Background:
    Given there is a quiz player

  Scenario Outline: A player should be able to give there name for a quiz
    When a player gives there <name>
    Then a player should be able to get there <name>

  Examples:
    | name    |
    | "Ryan"  |
    | "Keimi" |

  Scenario Outline: A player cannot submit an empty name
    When a player gives there <name>
    Then a player should not be able to get there name
    And should get the message "Name cannot be empty"

  Examples:
    | name   |
    | ""     |
    | "   "  |
    | "null" |

  Scenario Outline: Should be able to choose a quiz
    Given a player has a menu
    When a player chooses an available quiz <menuOption>
    Then a player should be able to get the questions for that quiz

  Examples:
    | menuOption |
    | 1          |
    | 5          |

  Scenario Outline: Should be able to get the name of a chosen a quiz
    Given a player has a menu
    When a player chooses an available quiz <menuOption>
    Then a player should be able to get the name for that quiz

  Examples:
    | menuOption |
    | 1          |
    | 5          |

  Scenario Outline: Should be able to get the score
    Given a player has a menu
    When a player chooses an available quiz <menuOption>
    And a player adds <score> to the score
    Then a player should be able to get the <score> for the quiz


  Examples:
    | menuOption | score |
    | 1          | 4     |
    | 5          | 54    |

  Scenario Outline: Should be able to check if the player has won when quiz is submitted
    Given a player has a menu
    When a player chooses an available quiz <menuOption>
    And a player submits the quiz
    Then a player should be able to know if they have <won>

  Examples:
    | menuOption | won    |
    | 1          | "true" |
    | 5          | "true" |

  Scenario Outline: Should be able to set a new high score if the player is the winner
    Given a player has a menu
    When a player chooses an available quiz <menuOption>
    And a player submits the quiz
    And a player has <won>
    Then the high score should be submitted

  Examples:
    | menuOption | won    |
    | 1          | "true" |
    | 5          | "true" |