package pij.ryan.durling.views.pages;

import gui.pij.ryan.durling.views.Footer;
import gui.pij.ryan.durling.views.FooterImpl;

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
    public Header getHeader() {
        return new HeaderImpl();
    }

    @Override
    public Footer getFooter() {
        return new FooterImpl();
    }
}
