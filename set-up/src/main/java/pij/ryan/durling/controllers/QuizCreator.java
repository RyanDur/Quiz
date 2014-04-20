package pij.ryan.durling.controllers;

import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;
import pij.ryan.durling.models.QuizOption;

import java.util.Set;

public interface QuizCreator {
    void createQuiz(String name) throws IllegalArgumentException;

    String getName();

    void addQuestion(String question, int value) throws IllegalQuizCreationException, IllegalArgumentException;

    void addAnswer(String answer, boolean value) throws IllegalArgumentException, IllegalQuizCreationException;

    void save() throws IllegalQuizCreationException, InvalidQuizException;

    boolean validQuiz();

    String getQuestion();

    Set<QuizOption> getAvailableQuizzes();

    Set<QuizOption> getClosedQuizzes();

    void closeQuiz(int quizId);

    void openQuiz(int quizId);
}
