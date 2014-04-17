Feature: Question for a quiz

  Scenario Outline: should be able to add a question with a score
    Given a user has a <question> and a <score>
    Then the question should have that <question> and <score>

  Examples:
    | question                       | score |
    | "What's the deal?"             | 7     |
    | "Whos afraid of virginia wolf" | 23    |


  Scenario Outline: should be able to add an answer to a question
    Given a user has a <question> and a <score>
    When a user adds an <answer>
    Then the question should have that <answer>

  Examples:
    | question                        | score | answer           |
    | "What's the deal"               | 7     | "answer"         |
    | "Whos afraid of virginia wolf?" | 23    | "another answer" |


  Scenario Outline: should not be a valid without an answer
    Given a user has a <question> and a <score>
    Then the question should be <valid>

  Examples:
    | question                       | score | valid   |
    | "What's the deal?"             | 7     | "false" |
    | "Whos afraid of virginia wolf" | 23    | "false" |

    
  Scenario Outline: should be able to add an answer to a question
    Given a user has a <question> and a <score>
    When a user adds an <answer>
    Then the question should be <valid>

  Examples:
    | question                        | score | answer           | valid  |
    | "What's the deal"               | 7     | "answer"         | "true" |
    | "Whos afraid of virginia wolf?" | 23    | "another answer" | "true" |
