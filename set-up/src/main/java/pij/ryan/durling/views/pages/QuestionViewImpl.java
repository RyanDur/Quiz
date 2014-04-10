package pij.ryan.durling.views.pages;

import javafx.geometry.Pos;
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
        this.setId("questionView");
        this.setAlignment(Pos.BASELINE_CENTER);

        questionArea = getQuestionArea();
        this.add(questionArea, 1, 0);
        this.add(getUserInputArea(), 1, 1);
    }

    @Override
    public String getQuestion() {
        return questionArea.getText();
    }

    @Override
    public int getScore() {
        return Integer.parseInt(scoreArea.getText());
    }

    @Override
    public Button getAddQuestionButton() {
        return addQuestionButton;
    }

    private GridPane getUserInputArea() {
        GridPane gridPane = new GridPane();
        scoreArea = getScoreArea();
        addQuestionButton = addQuestionButton();
        gridPane.add(scoreArea, 0, 0);
        gridPane.add(addQuestionButton, 1, 0);
        return gridPane;
    }

    private Button addQuestionButton() {
        return getButton("Add Question", "add-question-button");
    }

    private TextArea getQuestionArea() {
        TextArea questionArea = new TextArea();

        questionArea.setPromptText("Add Question");
        questionArea.setId("add-question");

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
