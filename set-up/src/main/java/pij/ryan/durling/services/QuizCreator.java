package pij.ryan.durling.services;

import pij.ryan.durling.registry.Answer;
import pij.ryan.durling.registry.Question;
import pij.ryan.durling.registry.Quiz;

public interface QuizCreator {
    int create(String name);

    Quiz getQuiz();

    void save();

    Question createQuestion(String question, int value);

    Answer createAnswer(Question question, String answer, boolean value);
}
