package pij.ryan.durling.models;

import java.util.Set;

public interface Question {

    /**
     * get the question
     *
     * @return a string
     */
    String getQuestion();

    /**
     * the score for the question
     *
     * @return an int
     */
    int getValue();

    /**
     * add an answer for the question
     *
     * @param answer takes an answer
     */
    void add(Answer answer);

    /**
     * get a set of answers
     *
     * @return a set of answers
     */
    Set<Answer> getAnswers();
}
