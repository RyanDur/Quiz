package pij.ryan.durling.controllers;

import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;

import java.util.Set;

public interface QuizCtrl {
    Quiz createQuiz(String title);

    Question createQuestion(String question, int score);

    Answer createAnswer(String answer, boolean value);

    void add(Quiz quiz);

    Set<QuizOption> getQuizOptions();

    Quiz getQuiz(int id);
}
