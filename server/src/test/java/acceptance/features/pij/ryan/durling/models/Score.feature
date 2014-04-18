Feature: Score object

  Scenario Outline: create a score object
    Given there is a score object for <name> with <score>
    Then a player has a <score>

  Examples:
    | name  | score |
    | "poo" | 5     |