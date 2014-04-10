package pij.ryan.durling.views.pages;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class AnswerViewImpl extends GridPane implements AnswerView {

    private boolean answerValue;
    private final Button addAnswerButton;
    private final Button addAnotherQuestionButton;
    private final TextArea answerArea;


    public AnswerViewImpl() {
        this.getStylesheets().add("styles/answerView.css");
        this.setId("answerView");
        answerArea = addAnswerArea();

        GridPane innerGrid = new GridPane();
        GridPane radios = addRadioButtons();
        addAnswerButton = addAnswerButton();
        addAnotherQuestionButton = addAnotherQuestionButton();
        this.setAlignment(Pos.BASELINE_CENTER);

        innerGrid.add(addAnswerButton, 2, 0);
        innerGrid.add(addAnotherQuestionButton, 3, 0);
        innerGrid.add(radios, 1, 0);
        this.add(answerArea, 1, 1);
        this.add(innerGrid, 1,2);
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

    private Button addAnswerButton() {
        return getButton("Add Answer", "add-answer-button");
    }

    private Button addAnotherQuestionButton() {
        return getButton("Another Question", "add-another-question");
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

    private TextArea addAnswerArea() {
        TextArea answerArea = new TextArea();
        answerArea.setPromptText("Add Answer");
        answerArea.setId("add-answer");
        return answerArea;
    }

    private Button getButton(String label, String id) {
        Button button = new Button(label);
        button.setId(id);
        return button;
    }
}
