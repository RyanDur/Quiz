package pij.ryan.durling.views.pages;

import pij.ryan.durling.models.Question;

import java.util.Set;

public interface Views {
    Header getHeader();

    ViewPane getViewPane();

    SignInView getSignInView();

    MenuView getMenuView();

    QuestionsView getQuestionView(Set<Question> questions);

    Footer getFooter();

    ResultsView getResultsView();
}
