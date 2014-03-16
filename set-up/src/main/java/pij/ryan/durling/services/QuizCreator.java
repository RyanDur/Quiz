package pij.ryan.durling.services;

import pij.ryan.durling.registry.Question;
import pij.ryan.durling.registry.Quiz;

public interface QuizCreator {
    int create(String name);

    Quiz getQuiz();

    void addQuestion(Question question);

    void save();

    Question createQuestion(String question);
}
