package pij.ryan.durling.models;

import java.util.Set;

public interface Quiz {

    /**
     * the name of the quiz
     *
     * @return a string
     */
    String getName();

    /**
     * the id for the quiz
     *
     * @return int
     */
    int getId();

    /**
     * gets a set of questions for the quiz
     *
     * @return a set of questions
     */
    Set<Question> getQuestions();
}
