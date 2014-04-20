package pij.ryan.durling.controllers;

import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.QuizOption;

import java.util.Set;

public interface QuizPlayer {

    /**
     * Get the available quizzes from the server
     *
     * @return Set<QuizOption>
     */
    Set<QuizOption> getMenu();

    /**
     * get a quiz
     *
     * @param choice int
     */
    void chooseQuiz(int choice);

    /**
     * get the questions from the chosen quiz
     *
     * @return Set<Question>
     */
    Set<Question> getQuestions();

    /**
     * get the name of the chosen quiz
     *
     * @return String
     */
    String getQuizName();

    /**
     * Set the players name
     *
     * @param name String
     * @throws IllegalArgumentException
     */
    void setPlayerName(String name) throws IllegalArgumentException;

    /**
     * get the players name
     *
     * @return String
     */
    String getPlayerName();

    /**
     * Submit the quiz
     */
    void submitQuiz();

    /**
     * Add the score of the player
     *
     * @param score int
     */
    void addToScore(int score);

    /**
     * get the players score
     *
     * @return int
     */
    int getScore();

    /**
     * check if the player has won
     *
     * @return boolean
     */
    boolean hasWon();

    /**
     * get he old winners name
     *
     * @return String
     */
    String getOldCurrentWinner();

    /**
     * get the old high score
     *
     * @return int
     */
    int getOldHighScore();
}
