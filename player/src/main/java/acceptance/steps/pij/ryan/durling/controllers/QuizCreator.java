package acceptance.steps.pij.ryan.durling.controllers;

import acceptance.steps.pij.ryan.durling.registry.Quiz;

public interface QuizCreator {
    int create(String name);

    Quiz get(int quizId);
}
