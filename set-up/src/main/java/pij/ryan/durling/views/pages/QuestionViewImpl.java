package pij.ryan.durling.views.pages;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import pij.ryan.durling.messages.ViewMessages;

public class QuestionViewImpl extends GridPane implements QuestionView {
    private TextArea questionArea;
    private TextField scoreArea;
    private Button addQuestionButton;

    public QuestionViewImpl() {
        this.getStylesheets().add(ViewMessages.QUESTION_VIEW_STYLE_SHEET);
        this.setId(ViewMessages.QUESTION_VIEW_ID);
        this.setAlignment(Pos.TOP_CENTER);
        questionArea = getQuestionArea();
        this.add(questionArea, 1, 1);
        this.add(getUserInputArea(), 1, 2);
    }

    @Override
    public Button getAddQuestionButton() {
        return addQuestionButton;
    }

    @Override
    public String getQuestion() {
        return questionArea.getText();
    }

    @Override
    public int getScore() {
        return Integer.parseInt(scoreArea.getText());
    }


    private GridPane getUserInputArea() {
        GridPane gridPane = new GridPane();
        gridPane.setId(ViewMessages.QUESTION_USER_INPUT_AREA_ID);

        scoreArea = getScoreArea();
        addQuestionButton = questionButton();

        gridPane.add(scoreArea, 0, 0);
        gridPane.add(addQuestionButton, 1, 0);
        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }

    private Button questionButton() {
        addQuestionButton = getButton(ViewMessages.ADD_QUESTION_BUTTON, ViewMessages.ADD_QUESTION_BUTTON_ID);
        return addQuestionButton;
    }

    private TextArea getQuestionArea() {
        TextArea questionArea = new TextArea();

        questionArea.setPromptText(ViewMessages.ADD_QUESTION_PROMPT);
        questionArea.setId(ViewMessages.QUESTION_INPUT_AREA_ID);

        return questionArea;
    }

    private TextField getScoreArea() {
        TextField scoreArea = new TextField();
        scoreArea.setId(ViewMessages.SCORE_ID);

        scoreArea.setPromptText(ViewMessages.ADD_SCORE);

        return scoreArea;
    }

    private Button getButton(String label, String id) {
        Button button = new Button(label);
        button.setId(id);
        return button;
    }
}
