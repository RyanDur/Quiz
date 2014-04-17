package pij.ryan.durling.views.elements;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pij.ryan.durling.messages.ViewMessages;


public class HeaderImpl extends HBox implements Header {

    private final Label nameLabel;
    private final Label quizLabel;

    public HeaderImpl() {
        this.getStylesheets().add(ViewMessages.HEADER_STYLESHEET);
        this.setId(ViewMessages.HEADER_ID);

        BorderPane borderPane = new BorderPane();
        borderPane.setId(ViewMessages.HEADER_LABELS_ID);
        this.getChildren().add(borderPane);

        quizLabel = new Label();
        quizLabel.setId(ViewMessages.QUIZ_TITLE_ID);
        borderPane.setRight(quizLabel);

        nameLabel = new Label();
        nameLabel.setId(ViewMessages.NAME_TITLE_ID);
        borderPane.setLeft(nameLabel);

        BorderPane.setAlignment(nameLabel, Pos.BOTTOM_LEFT);
        BorderPane.setAlignment(quizLabel, Pos.TOP_RIGHT);
    }


    @Override
    public void setPlayerName(String name) {
        nameLabel.setText(name);
    }

    @Override
    public void setQuizTitle(String name) {
        quizLabel.setText(name);
    }
}
