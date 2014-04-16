package pij.ryan.durling.views.pages;

import javafx.scene.Node;
import pij.ryan.durling.models.Question;

import java.util.Set;

public interface Views {
    Header getHeader();

    ViewPane getViewPane();

    SignInView getSignInView();

    MenuView getMenuView();

    Node getQuestionView(Set<Question> questions);
}
