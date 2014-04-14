package pij.ryan.durling.controllers;

import pij.ryan.durling.models.QuizOption;

import java.util.Set;

public interface QuizElements {
    Menu getMenu(Set<QuizOption> quizOptions);
}
