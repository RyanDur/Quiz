package pij.ryan.durling.views.pages;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.models.Answer;

import java.util.Set;

public class AnswerViewImpl extends GridPane implements AnswerView {

    private Set<Answer> answers;

    public AnswerViewImpl(Set<Answer> answers) {
        this.answers = answers;
        this.setId(ViewMessages.ANSWER_VIEW_ID);
        this.getStylesheets().add(ViewMessages.ANSWER_VIEW_STYLE_SHEET);
        setAnswers();
    }

    private void setAnswers() {
        int y = 0;
        for (Answer answer : answers) {
            this.add(getAnswerButton(answer), 0, y);
            this.add(new Label(answer.getAnswer()), 1, y++);
        }
    }

    private RadioButton getAnswerButton(Answer answer) {
        RadioButton radio = new RadioButton();
        radio.setId(ViewMessages.ANSWER_ID + answer.getId());
        radio.setOnAction(e -> answer.select());
        return radio;
    }
}
