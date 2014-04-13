Feature: A menu for a player to choose a quiz

  Scenario Outline: Should be able to choose from a list of quizzes
    Given there is a menu of quizzes
    When a player chooses quiz <quiz>
    Then a player should get to play the quiz

  Examples:
    | quiz |
    | 3    |
    | 6    |