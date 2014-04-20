package pij.ryan.durling.views.factories;

import javafx.scene.layout.GridPane;
import pij.ryan.durling.views.elements.Footer;
import pij.ryan.durling.views.elements.Header;
import pij.ryan.durling.views.elements.ViewPane;
import pij.ryan.durling.views.pages.*;

public interface Views {

    /**
     * Get the header
     *
     * @return Header
     */
    Header getHeader();

    /**
     * Get the view switcher
     *
     * @return ViewPane
     */
    ViewPane getViewPane();

    /**
     * Get the sign in view
     *
     * @return SignIn
     */
    SignIn getSignInView();

    /**
     * Get the Menu
     *
     * @return Menu
     */
    Menu getMenuView();

    /**
     * get the question view
     *
     * @param question pij.ryan.durling.models.Quesiton
     * @param answerView GridPane
     * @return Question
     */
    Question getQuestionView(pij.ryan.durling.models.Question question, GridPane answerView);

    /**
     * Get the footer
     *
     * @return Footer
     */
    Footer getFooter();

    /**
     * Get the results view
     *
     * @return Results
     */
    Results getResultsView();

    /**
     * Get the answer view
     *
     * @return Answer
     */
    Answer getAnswerView();
}
