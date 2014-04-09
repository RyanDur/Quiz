package pij.ryan.durling.controllers;

import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;

public interface QuizCreator {
    int createQuiz(String name);

    String getName();

    void addQuestion(String question, int value) throws IllegalQuizCreationException;

    void addAnswer(String answer, boolean value) throws IllegalArgumentException, IllegalQuizCreationException;

    void save() throws IllegalQuizCreationException, InvalidQuizException;

    boolean validQuiz();

    String getQuestion();

    void lockQuiz(int id);
}
