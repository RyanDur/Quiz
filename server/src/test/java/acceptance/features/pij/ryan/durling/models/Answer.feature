Feature: An answer for a user to select

  Scenario Outline: should be able to make an answer
    Given a user has a <correct> <answer>
    Then it should be <correct>
    And it should have an <answer>

  Examples:
    | correct | answer          |
    | "true"  | "you are funny" |
    | "false" | "you are tall"  |
