Feature: Calculator

  As a student
  I want to calculate numbers
  So that I don't have to do it by hand

  Background:
    Given I have a calculator

  Scenario Outline: Addition

    When I add <left> and <right>
    Then I should have <answer>

  Examples:
    | left | right | answer |
    | 2    | 2     | 4      |
    | 1    | 5     | 6      |

  Scenario Outline: Subtraction

    When I subtract <left> from <right>
    Then I should have <answer>

  Examples:
    | left | right | answer |
    | 2    | 2     | 0      |
    | 5    | 1     | 4      |