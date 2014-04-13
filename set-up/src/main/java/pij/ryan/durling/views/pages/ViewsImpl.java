package pij.ryan.durling.views.pages;

public class ViewsImpl implements Views {

    @Override
    public ViewBox getErrorBox() {
        return new ViewBoxImpl();
    }

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
