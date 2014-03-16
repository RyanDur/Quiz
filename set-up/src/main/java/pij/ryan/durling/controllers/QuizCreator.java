package pij.ryan.durling.controllers;

import pij.ryan.durling.registry.Quiz;

import java.util.List;

public interface QuizCreator {
    int create(String name);

    Quiz get(int quizId);

    void addQuestion(String question, int quizId);

    void save(int quizId);

    List<Quiz> getQuizzes();
}
