package pij.ryan.durling.views.pages;

import javafx.scene.layout.GridPane;
import pij.ryan.durling.models.Question;

public interface Views {
    Header getHeader();

    ViewPane getViewPane();

    SignInView getSignInView();

    MenuView getMenuView();

    QuestionView getQuestionView(Question question, GridPane answerView);

    Footer getFooter();

    ResultsView getResultsView();

    AnswerView getAnswerView();
}
