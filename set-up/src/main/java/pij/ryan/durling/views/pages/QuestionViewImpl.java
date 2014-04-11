package pij.ryan.durling.views.pages;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class QuestionViewImpl extends GridPane implements QuestionView {
    private TextArea questionArea;
    private TextField scoreArea;
    private Button addQuestionButton;

    public QuestionViewImpl() {
        this.getStylesheets().add("styles/questionView.css");
        this.setId("question-view");

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
        gridPane.setId("user-input-area");

        scoreArea = getScoreArea();
        addQuestionButton = questionButton();

        gridPane.add(scoreArea, 0, 0);
        gridPane.add(addQuestionButton, 1, 0);
        return gridPane;
    }

    private Button questionButton() {
        addQuestionButton = getButton("Add Question", "add-question-button");
        return addQuestionButton;
    }

    private TextArea getQuestionArea() {
        TextArea questionArea = new TextArea();

        questionArea.setPromptText("Add Question");
        questionArea.setId("add-question-area");

        return questionArea;
    }

    private TextField getScoreArea() {
        TextField scoreArea = new TextField();
        scoreArea.setId("score");

        scoreArea.setPromptText("Add Score");

        return scoreArea;
    }

    private Button getButton(String label, String id) {
        Button button = new Button(label);
        button.setId(id);
        return button;
    }
}
