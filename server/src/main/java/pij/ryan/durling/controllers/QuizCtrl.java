package pij.ryan.durling.controllers;

import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;

public interface QuizCtrl {
    Quiz createQuiz(String title);

    Question createQuestion(String question, int score);
}
