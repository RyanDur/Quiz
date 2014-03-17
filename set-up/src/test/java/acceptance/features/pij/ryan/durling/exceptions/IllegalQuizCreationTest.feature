Feature: Should throw a meaningful exception when creating a quiz

  Scenario: throw IllegalQuizCreationException
    Given a metod throws an IllegalQuizCreationException
    Then it should have a meaningful message