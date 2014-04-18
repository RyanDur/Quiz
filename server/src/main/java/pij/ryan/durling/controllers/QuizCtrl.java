package pij.ryan.durling.controllers;

import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;

import java.util.Set;

public interface QuizCtrl {
    void add(Quiz quiz);

    Set<QuizOption> getQuizOptions();

    Quiz getQuiz(int id);
}
