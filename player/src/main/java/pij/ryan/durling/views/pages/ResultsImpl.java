package pij.ryan.durling.views.pages;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import pij.ryan.durling.messages.ViewMessages;

public class ResultsImpl extends StackPane implements Results {

    private Label winnerLabel;
    private Label scoreLabel;
    private Label oldWinnerLabel;
    private Label oldScoreLabel;

    public ResultsImpl() {
        this.getStylesheets().add(ViewMessages.RESULTS_VIEW_STYLESHEETS);
        this.setId(ViewMessages.RESULTS_VIEW_ID);
        GridPane borderPane = getGridPane();
        this.getChildren().add(borderPane);
    }

    private GridPane getGridPane() {
        GridPane borderPane = new GridPane();
        borderPane.setAlignment(Pos.CENTER);
        winnerLabel = new Label();
        winnerLabel.setId(ViewMessages.RESULT_ID);
        oldWinnerLabel = new Label(ViewMessages.LOSER_LABEL);
        scoreLabel = new Label();
        oldScoreLabel = new Label();
        scoreLabel.setId(ViewMessages.SCORE_ID);
        borderPane.add(winnerLabel, 0, 0);
        borderPane.add(scoreLabel, 0, 1);
        borderPane.add(oldWinnerLabel, 0, 2);
        borderPane.add(oldScoreLabel, 0, 3);
        return borderPane;
    }

    /*
    You Won
    Your score is:
    The old winner is:
    The old winning score is:
     */

    @Override
    public void setResults(boolean winner, int score, String oldWinner, int oldScore) {
        if (winner) {
            winnerLabel.setText(ViewMessages.WINNER);
        } else {
            winnerLabel.setText(ViewMessages.LOSER);
        }
        scoreLabel.setText(ViewMessages.SCORE + String.valueOf(score));
        if (oldWinner != null) {
            oldWinnerLabel.setText(ViewMessages.OLD_WINNER + oldWinner);
            oldScoreLabel.setText(ViewMessages.OLD_SCORE + String.valueOf(oldScore));
        }
    }
}
