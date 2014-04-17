package pij.ryan.durling.views.pages;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import pij.ryan.durling.messages.ViewMessages;

public class ResultsImpl extends StackPane implements Results {

    private Label winnerLabel;
    private Label scoreLabel;

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
        scoreLabel = new Label();
        scoreLabel.setId(ViewMessages.SCORE_ID);
        borderPane.add(winnerLabel, 0, 0);
        borderPane.add(scoreLabel, 0, 1);
        return borderPane;
    }

    @Override
    public void setResults(boolean winner, int score) {
        if (winner) {
            winnerLabel.setText(ViewMessages.WINNER);
        } else {
            winnerLabel.setText(ViewMessages.LOSER);
        }
        scoreLabel.setText(String.valueOf(score));
    }
}
