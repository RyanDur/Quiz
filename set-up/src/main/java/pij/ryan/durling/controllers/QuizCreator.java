package pij.ryan.durling.controllers;

import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;
import pij.ryan.durling.models.QuizOption;

import java.util.Set;

public interface QuizCreator {

    /**
     * create a quiz
     *
     * @param name String
     * @throws IllegalArgumentException
     */
    void createQuiz(String name) throws IllegalArgumentException;

    /**
     * Name of quiz
     *
     * @return String
     */
    String getName();

    /**
     * Add a question to a quiz
     *
     * @param question String
     * @param value int
     */
    void addQuestion(String question, int value) throws IllegalQuizCreationException, IllegalArgumentException;

    /**
     * Add an answer to a quiz
     *
     * @param answer String
     * @param value boolean
     * @throws IllegalArgumentException
     * @throws IllegalQuizCreationException
     */
    void addAnswer(String answer, boolean value) throws IllegalArgumentException, IllegalQuizCreationException;

    /**
     * Save the quiz
     *
     * @throws IllegalQuizCreationException
     * @throws InvalidQuizException
     */
    void save() throws IllegalQuizCreationException, InvalidQuizException;

    /**
     * Check if the quiz is valid
     *
     * @return boolean
     */
    boolean validQuiz();

    /**
     * the current question
     *
     * @return String
     */
    String getQuestion();

    /**
     * get a list of available quizzes from the server
     *
     * @return Set of Quiz Options
     */
    Set<QuizOption> getAvailableQuizzes();

    /**
     * get a list of closed quizzes from the server
     *
     * @return Set of Quiz Options
     */
    Set<QuizOption> getClosedQuizzes();

    /**
     * close a quiz on the server
     *
     * @param quizId int
     */
    void closeQuiz(int quizId);

    /**
     * open a quiz on the server
     *
     * @param quizId int
     */
    void openQuiz(int quizId);
}
