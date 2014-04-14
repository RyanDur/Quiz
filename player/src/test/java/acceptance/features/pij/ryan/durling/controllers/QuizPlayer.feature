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

  Scenario Outline: Should be able to get the score of a submitted quiz
    Given a player has a menu
    When a player chooses an available quiz <menuOption>
    And a player submits the quiz
    Then a player should be able to get the score for the quiz

  Examples:
    | menuOption |
    | 1          |
    | 5          |

  Scenario Outline: Should be able to check if the player has won when quiz is submitted
    Given a player has a menu
    When a player chooses an available quiz <menuOption>
    And a player submits the quiz
    Then a player should be able to know if they have <won>

  Examples:
    | menuOption | won    |
    | 1          | "true" |
    | 5          | "true" |