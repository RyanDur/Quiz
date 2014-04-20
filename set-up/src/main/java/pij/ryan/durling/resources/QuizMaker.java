package pij.ryan.durling.resources;

import pij.ryan.durling.models.QuizOption;

import java.util.Set;

public interface QuizMaker {

    /**
     * create a quiz
     *
     * @param title String
     * @return int
     */
    int createQuiz(String title);

    /**
     * Add a question to a quiz
     *
     * @param question String
     * @param score int
     */
    void addQuestion(String question, int score);

    /**
     * Add an answer to a quiz
     *
     * @param answer String
     * @param value boolean
     */
    void addAnswer(String answer, boolean value);

    /**
     * get a list of available quizzes
     *
     * @return Set of quiz options
     */
    Set<QuizOption> getAvailableQuizzes();

    /**
     * get a list of closed quizzes
     *
     * @return Set of quiz options
     */
    Set<QuizOption> getClosedQuizzes();

    /**
     * Save a quiz
     */
    void save();

    /**
     * Check if quiz is valid
     *
     * @return boolean
     */
    boolean validQuiz();

    /**
     * check if there is no quiz
     *
     * @return boolean
     */
    boolean empty();

    /**
     * check if the question is valid
     *
     * @return boolean
     */
    boolean validQuestion();

    /**
     * close a quiz
     *
     * @param quizId int
     */
    void closeQuiz(int quizId);

    /**
     * open a quiz
     *
     * @param quizId int
     */
    void openQuiz(int quizId);
}
