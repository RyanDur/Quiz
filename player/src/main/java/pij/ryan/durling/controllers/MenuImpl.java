package pij.ryan.durling.controllers;

import pij.ryan.durling.models.QuizOption;

import java.util.Set;

public class MenuImpl implements Menu {

    private Set<QuizOption> quizOptions;

    public MenuImpl(Set<QuizOption> quizOptions) {

        this.quizOptions = quizOptions;
    }

    @Override
    public int getQuizId(int choice) {
        return 0;
    }

    @Override
    public Set<QuizOption> getQuizzes() {
        return null;
    }
}
