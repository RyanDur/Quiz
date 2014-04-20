package pij.ryan.durling.views.factories;

import pij.ryan.durling.views.elements.*;
import pij.ryan.durling.views.pages.*;

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

    @Override
    public SplitMenu getSplitMenu() {
        Menu left = new MenuImpl();
        Menu right = new MenuImpl();
        return new SplitMenuImpl(left, right);
    }
}
