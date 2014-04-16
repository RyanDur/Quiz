package pij.ryan.durling.views.pages;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import pij.ryan.durling.controllers.QuizPlayer;
import pij.ryan.durling.messages.ViewMessages;

public class QuizPlayerViewImpl extends StackPane implements QuizPlayerView {
    private QuizPlayer quizPlayer;
    private Views views;
    private final Header header;
    private final ViewPane viewPane;

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
        return views.getMenuView(quizPlayer.getMenu(), header);
    }
}
