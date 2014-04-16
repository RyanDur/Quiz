package pij.ryan.durling.views.pages;

import javafx.scene.layout.StackPane;
import pij.ryan.durling.controllers.QuizPlayer;
import pij.ryan.durling.messages.ViewMessages;

public class QuizPlayerViewImpl extends StackPane implements QuizPlayerView {
    private QuizPlayer quizPlayer;
    private Views views;

    public QuizPlayerViewImpl(QuizPlayer quizPlayer, Views views) {
        this.quizPlayer = quizPlayer;
        this.views = views;
        this.getStylesheets().add(ViewMessages.QUIZ_PLAYER_VIEW_STYLE_SHEET);
    }
}
