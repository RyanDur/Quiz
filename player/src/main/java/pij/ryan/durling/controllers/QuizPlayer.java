package pij.ryan.durling.controllers;

import pij.ryan.durling.models.Quiz;

import java.util.List;

public interface QuizPlayer {
    List<Quiz> getQuizList();

    Quiz getQuiz(int quizIndex);
}
