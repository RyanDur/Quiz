package pij.ryan.durling.views.factories;

import javafx.scene.layout.GridPane;
import pij.ryan.durling.views.elements.Footer;
import pij.ryan.durling.views.elements.Header;
import pij.ryan.durling.views.elements.ViewPane;
import pij.ryan.durling.views.pages.*;

public interface Views {
    Header getHeader();

    ViewPane getViewPane();

    SignIn getSignInView();

    Menu getMenuView();

    Question getQuestionView(pij.ryan.durling.models.Question question, GridPane answerView);

    Footer getFooter();

    Results getResultsView();

    Answer getAnswerView();
}
