package pij.ryan.durling.controllers;

import pij.ryan.durling.models.QuizOptions;

import java.util.List;

public interface QuizElements {
    Menu getMenu(List<QuizOptions> quizOptions);
}
