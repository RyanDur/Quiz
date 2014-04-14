package pij.ryan.durling.controllers;

import pij.ryan.durling.models.QuizOptions;

import java.util.Set;

public interface QuizElements {
    Menu getMenu(Set<QuizOptions> quizOptions);
}
