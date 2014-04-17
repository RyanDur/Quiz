package pij.ryan.durling.views.pages;

import javafx.scene.layout.GridPane;
import pij.ryan.durling.models.Question;

public class ViewsImpl implements Views {
    @Override
    public Header getHeader() {
        return new HeaderImpl();
    }

    @Override
    public ViewPane getViewPane() {
        return new ViewPaneImpl();
    }

    @Override
    public SignInView getSignInView() {
        return new SignInViewImpl();
    }

    @Override
    public MenuView getMenuView() {
        return new MenuViewImpl();
    }

    @Override
    public QuestionView getQuestionView(Question question, GridPane answerView) {
        return new QuestionViewImpl(question, answerView);
    }

    @Override
    public Footer getFooter() {
        return new FooterImpl();
    }

    @Override
    public ResultsView getResultsView() {
        return new ResultsViewImpl();
    }

    @Override
    public AnswerView getAnswerView() {
        return new AnswerViewImpl();
    }
}
