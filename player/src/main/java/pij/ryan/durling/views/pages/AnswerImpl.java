package pij.ryan.durling.views.pages;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import pij.ryan.durling.messages.ViewMessages;

public class AnswerImpl extends GridPane implements Answer {

    public AnswerImpl() {
        this.setId(ViewMessages.ANSWER_VIEW_ID);
        this.getStylesheets().add(ViewMessages.ANSWER_VIEW_STYLE_SHEET);
    }

    @Override
    public void addAnswer(RadioButton radioButton, String answer, int y) {
        this.add(radioButton, 0, y);
        this.add(new Label(answer), 1, y);
    }

}
