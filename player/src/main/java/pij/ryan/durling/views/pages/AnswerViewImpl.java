package pij.ryan.durling.views.pages;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.models.Answer;

import java.util.Set;

public class AnswerViewImpl extends StackPane implements AnswerView {

    public AnswerViewImpl(Set<Answer> answers) {
        this.getStylesheets().add(ViewMessages.ANSWER_VIEW_STYLE_SHEET);
        GridPane gridPane = getGridPane(answers);
        this.getChildren().add(gridPane);
    }

    private GridPane getGridPane(Set<Answer> answers) {
        GridPane gridPane = new GridPane();
        gridPane.setId(ViewMessages.ANSWER_VIEW_ID);
        int y = 0;

        for(Answer answer: answers) {
            Label label = new Label();
            label.setText(answer.getAnswer());
            RadioButton radio = new RadioButton();
            radio.setId(ViewMessages.ANSWER_ID + answer.getId());
            radio.setOnAction(e -> answer.select());
            gridPane.add(radio, 0, y);
            gridPane.add(label, 1, y++);
        }
        return gridPane;
    }
}
