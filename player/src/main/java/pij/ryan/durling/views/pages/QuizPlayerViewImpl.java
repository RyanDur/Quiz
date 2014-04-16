package pij.ryan.durling.views.pages;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import pij.ryan.durling.controllers.Menu;
import pij.ryan.durling.controllers.QuizPlayer;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.models.QuizOption;

public class QuizPlayerViewImpl extends StackPane implements QuizPlayerView {
    private QuizPlayer quizPlayer;
    private Views views;
    private final Header header;
    private final ViewPane viewPane;
    private final Footer footer;

    public QuizPlayerViewImpl(QuizPlayer quizPlayer, Views views) {
        this.quizPlayer = quizPlayer;
        this.views = views;
        this.setId(ViewMessages.QUIZ_PLAYER_ID);
        this.getStylesheets().add(ViewMessages.QUIZ_PLAYER_VIEW_STYLE_SHEET);


        BorderPane borderPane = new BorderPane();
        header = views.getHeader();
        borderPane.setTop((Node) header);
        viewPane = views.getViewPane();
        borderPane.setCenter((Node) viewPane);
        footer = views.getFooter();
        borderPane.setBottom((Node) footer);
        viewPane.setView((Node) getSignInView(header, viewPane));
        this.getChildren().add(borderPane);
    }

    private SignInView getSignInView(Header header, ViewPane viewPane) {
        SignInView signInView = views.getSignInView();

        signInView.getSignInButton().setOnAction(e -> {
            header.setPlayerName(signInView.getName());
            viewPane.setView((Node) getMenuView());
        });
        return signInView;
    }

    private MenuView getMenuView() {
        MenuView menuView = views.getMenuView();
        Menu menu = quizPlayer.getMenu();
        int y = 0;
        for (QuizOption option : menu.getQuizzes()) {
            Button button = getButton(option.getQuizTitle(), y);
            button.setOnAction(e -> {
                header.setQuizTitle(option.getQuizTitle());
                getQuestionView(option);
            });
            menuView.addOption(button, y++);
        }
        return menuView;
    }

    private void getQuestionView(QuizOption option) {
        Button submitButton = getButton();
        footer.setSubmitButton(submitButton);
        quizPlayer.chooseQuiz(option.getQuizId());
        QuestionsView questionsView = views.getQuestionView(quizPlayer.getQuestions());
        viewPane.setView((Node) questionsView);
    }

    private Button getButton() {
        Button submitButton = new Button();
        submitButton.setOnAction(e -> quizPlayer.submitQuiz());
        submitButton.setText(ViewMessages.SUBMIT);
        return submitButton;
    }


    private Button getButton(String name, int id) {
        Button button = new Button();
        button.setText(name);
        button.setId(ViewMessages.MENU_VIEW_BUTTON_ID + id);
        return button;
    }
}
