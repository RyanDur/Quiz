Feature: Generate a unique id

  Scenario Outline: generate multiple unique ids
    Given there is a id generator
    When it generates <ids> ids
    Then they are all unique

  Examples:
    | ids |
    | 4   |
    | 9   |
    | 34  |