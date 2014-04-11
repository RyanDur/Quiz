package pij.ryan.durling.views.pages;

import pij.ryan.durling.controllers.QuizCreator;

public interface Views {
    QuestionView getQuestionView();

    AnswerView getAnswerView();

    QuizView getQuizView(QuizCreator quizCreator);
}
