package pij.ryan.durling.views.factories;

import javafx.scene.layout.GridPane;
import pij.ryan.durling.views.elements.*;
import pij.ryan.durling.views.pages.*;

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
    public SignIn getSignInView() {
        return new SignInImpl();
    }

    @Override
    public Menu getMenuView() {
        return new MenuImpl();
    }

    @Override
    public Question getQuestionView(pij.ryan.durling.models.Question question, GridPane answerView) {
        return new QuestionImpl(question, answerView);
    }

    @Override
    public Footer getFooter() {
        return new FooterImpl();
    }

    @Override
    public Results getResultsView() {
        return new ResultsImpl();
    }

    @Override
    public Answer getAnswerView() {
        return new AnswerImpl();
    }
}
