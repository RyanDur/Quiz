package pij.ryan.durling.controllers;

import pij.ryan.durling.models.QuizOption;

import java.util.Set;

public class QuizElementsImpl implements QuizElements {
    @Override
    public Menu getMenu(Set<QuizOption> quizOptions) {
        return new MenuImpl(quizOptions);
    }
}
