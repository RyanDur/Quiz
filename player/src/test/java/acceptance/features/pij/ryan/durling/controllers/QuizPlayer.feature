Feature: Coordinate the playing of a quiz

  Background:
    Given there is a quiz player

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