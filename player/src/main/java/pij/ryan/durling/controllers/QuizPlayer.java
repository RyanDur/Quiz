package pij.ryan.durling.controllers;

import pij.ryan.durling.models.Question;

import java.util.Set;

public interface QuizPlayer {

    Menu getMenu();

    void chooseQuiz(int choice);

    Set<Question> getQuestions();

    String getName();

    void setPlayerName(String name);

    String getPlayerName();
}
