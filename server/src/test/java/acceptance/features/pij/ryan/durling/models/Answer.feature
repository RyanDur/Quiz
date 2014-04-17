Feature: An answer for a user to select

  Scenario: should be able to make an answer
    Given a user has an answer
    When a user marks its <value>
    Then it should have that value