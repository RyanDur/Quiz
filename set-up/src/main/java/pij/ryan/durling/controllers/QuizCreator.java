package pij.ryan.durling.controllers;

import pij.ryan.durling.registry.Quiz;

import java.util.List;

public interface QuizCreator {
    int create(String name);

    Quiz getQuiz();

    void addQuestion(String question);

    void save();

    List<Quiz> getQuizzes();
}
