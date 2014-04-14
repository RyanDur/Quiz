package pij.ryan.durling.controllers;

import pij.ryan.durling.models.QuizOption;

import java.util.Set;

public interface Menu {
    int getQuizId(int choice);

    Set<QuizOption> getQuizzes();
}
