Feature: Should throw a meaningful exceptions

  Scenario: throw IllegalQuizCreationException
    Given a method throws an IllegalQuizCreationException
    Then it should say "Need to create a quiz first."

  Scenario: should throw InvalidQuizException
    Given a method throws an InvalidQuizException
    Then it should say "Invalid Quiz"