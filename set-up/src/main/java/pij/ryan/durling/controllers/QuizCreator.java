package pij.ryan.durling.controllers;

import pij.ryan.durling.registry.Quiz;

public interface QuizCreator {
    int create(String name);

    Quiz get(int quizId);

    void addQuestion(String question, int quizId);
}
