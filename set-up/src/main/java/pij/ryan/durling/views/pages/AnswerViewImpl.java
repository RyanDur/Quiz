package pij.ryan.durling.views.pages;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import pij.ryan.durling.messages.ViewMessages;

public class AnswerViewImpl extends GridPane implements AnswerView {

    private boolean answerValue;
    private Button addAnswerButton;
    private Button addAnotherQuestionButton;
    private TextArea answerArea;

    public AnswerViewImpl() {
        this.getStylesheets().add(ViewMessages.ANSWER_VIEW_STYLE_SHEET);
        this.setId(ViewMessages.ANSWER_VIEW_ID);
        this.setAlignment(Pos.CENTER);
        addAnotherQuestionButton = getButton(ViewMessages.ANOTHER_QUESTION_BUTTON, ViewMessages.ANOTHER_QUESTION_BUTTON_ID);
        addAnswerButton = getButton(ViewMessages.ANSWER_BUTTON, ViewMessages.ANSWER_BUTTON_ID);

        addAnswerArea();
        addUserControls();
    }

    @Override
    public void setQuestionLabel(String questionLabel) {
        this.add(new Label(questionLabel), 1, 0);
    }

    @Override
    public Button getAddAnswerButton() {
        return addAnswerButton;
    }

    @Override
    public Button getAddAnotherQuestionButton() {
        return addAnotherQuestionButton;
    }

    @Override
    public String getAnswer() {
        return answerArea.getText();
    }

    @Override
    public boolean getAnswerValue() {
        return answerValue;
    }

    private void addUserControls() {
        GridPane innerGrid = new GridPane();
        innerGrid.setId(ViewMessages.USER_CONTROLS_ID);
        GridPane radios = addRadioButtons();

        innerGrid.add(addAnswerButton, 2, 0);
        innerGrid.add(addAnotherQuestionButton, 3, 0);
        innerGrid.add(radios, 1, 0);
        innerGrid.setAlignment(Pos.CENTER);
        this.add(innerGrid, 1,2);
    }

    private void addAnswerArea() {
        answerArea = new TextArea();
        answerArea.setPromptText(ViewMessages.ANSWER_AREA_PROMPT_TEXT);
        answerArea.setId(ViewMessages.ANSWER_AREA_ID);
        this.add(answerArea, 1, 1);
    }

    private GridPane addRadioButtons() {
        RadioButton correct = getRadioButton(ViewMessages.CORRECT, ViewMessages.CORRECT_ID, true);
        RadioButton incorrect = getRadioButton(ViewMessages.INCORRECT, ViewMessages.INCORRECT_ID, false);

        GridPane radios = new GridPane();
        radios.setId(ViewMessages.RADIOS_ID);
        radios.add(correct, 1, 0);
        radios.add(incorrect, 2, 0);

        return radios;
    }

    private RadioButton getRadioButton(String title, String id, boolean value) {
        RadioButton radioButton = new RadioButton(title);
        radioButton.setId(id);
        radioButton.setOnAction(e -> answerValue = value);
        return radioButton;
    }

    private Button getButton(String label, String id) {
        Button button = new Button(label);
        button.setId(id);
        return button;
    }
}
