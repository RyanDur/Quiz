package pij.ryan.durling.views.factories;

import pij.ryan.durling.views.elements.*;
import pij.ryan.durling.views.pages.Answers;
import pij.ryan.durling.views.pages.AnswersImpl;
import pij.ryan.durling.views.pages.Questions;
import pij.ryan.durling.views.pages.QuestionsImpl;

public class ViewsImpl implements Views {

    @Override
    public ViewBox getErrorBox() {
        return new ViewBoxImpl();
    }

    @Override
    public Questions getQuestionView() {
        return new QuestionsImpl();
    }

    @Override
    public Answers getAnswerView() {
        return new AnswersImpl();
    }

    @Override
    public Header getHeader() {
        return new HeaderImpl();
    }

    @Override
    public Footer getFooter() {
        return new FooterImpl();
    }
}
