package pij.ryan.durling.views.pages;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class AnswerViewImpl extends GridPane implements AnswerView {

    private boolean answerValue;
    private Button addAnswerButton;
    private Button addAnotherQuestionButton;
    private TextArea answerArea;

    public AnswerViewImpl() {
        this.getStylesheets().add("styles/answerView.css");
        this.setId("answer-view");

        addAnotherQuestionButton = getButton("Another Question", "add-another-question");
        addAnswerButton = getButton("Add Answer", "add-answer-button");

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
        innerGrid.setId("user-controls");
        GridPane radios = addRadioButtons();

        innerGrid.add(addAnswerButton, 2, 0);
        innerGrid.add(addAnotherQuestionButton, 3, 0);
        innerGrid.add(radios, 1, 0);
        this.add(innerGrid, 1,2);
    }

    private void addAnswerArea() {
        answerArea = new TextArea();
        answerArea.setPromptText("Add Answer");
        answerArea.setId("add-answer-area");
        this.add(answerArea, 1, 1);
    }

    private GridPane addRadioButtons() {
        RadioButton correct = getRadioButton("Correct", "correct", true);
        RadioButton incorrect = getRadioButton("Incorrect", "incorrect", false);

        GridPane radios = new GridPane();
        radios.setId("radios");
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
