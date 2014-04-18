Feature: create a score

  Scenario Outline: create a score
    Given there is a score factory
    When a <user> creates a <score>
    Then it should return a score

  Examples:
    | user    | score |
    | "ryan"  | 5     |
    | "Keimi" | 56    |