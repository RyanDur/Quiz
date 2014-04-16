package pij.ryan.durling.controllers;

import pij.ryan.durling.models.Question;

import java.util.Set;

public interface QuizPlayer {

    Set<pij.ryan.durling.models.QuizOption> getMenu();

    void chooseQuiz(int choice);

    Set<Question> getQuestions();

    String getQuizName();

    void setPlayerName(String name) throws IllegalArgumentException;

    String getPlayerName();

    void submitQuiz();

    int getScore();

    boolean hasWon();
}
