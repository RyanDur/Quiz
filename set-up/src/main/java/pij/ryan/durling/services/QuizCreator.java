package pij.ryan.durling.services;

import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;
import pij.ryan.durling.registry.Answer;
import pij.ryan.durling.registry.Question;
import pij.ryan.durling.registry.Quiz;

public interface QuizCreator {
    int createQuiz(String name);

    Question createQuestion(String question, int value) throws IllegalQuizCreationException;

    Answer createAnswer(String answer, boolean value) throws IllegalArgumentException, IllegalQuizCreationException;

    Quiz getQuiz();

    void save() throws IllegalQuizCreationException, InvalidQuizException;

    void addAnswer(Question question, Answer answer) throws IllegalArgumentException;

    void addQuestion(Question question) throws IllegalQuizCreationException, IllegalArgumentException;
}
