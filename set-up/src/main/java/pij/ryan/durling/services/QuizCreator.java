package pij.ryan.durling.services;

import unit.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.registry.Answer;
import pij.ryan.durling.registry.Question;
import pij.ryan.durling.registry.Quiz;

public interface QuizCreator {
    int createQuiz(String name);

    Question createQuestion(String question, int value) throws IllegalQuizCreationException;

    Answer createAnswer(Question question, String answer, boolean value);

    Quiz getQuiz();

    void save();
}
