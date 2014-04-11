package pij.ryan.durling.views.pages;

import pij.ryan.durling.controllers.QuizCreator;

public class ViewsImpl implements Views {

    @Override
    public QuestionView getQuestionView() {
        return new QuestionViewImpl();
    }

    @Override
    public  AnswerView getAnswerView() {
        return new AnswerViewImpl();
    }

    @Override
    public QuizView getQuizView(QuizCreator quizCreator) {
        return new QuizViewImpl(quizCreator);
    }
}
