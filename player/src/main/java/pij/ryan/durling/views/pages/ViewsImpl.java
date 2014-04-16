package pij.ryan.durling.views.pages;

import javafx.scene.Node;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.views.factories.AnswerViewFactory;
import pij.ryan.durling.views.factories.AnswerViewFactoryImpl;

import java.util.Set;

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
    public Node getQuestionView(Set<Question> questions) {
        AnswerViewFactory answerViewFactory = new AnswerViewFactoryImpl();
        return new QuestionsViewImpl(questions, answerViewFactory);
    }
}
