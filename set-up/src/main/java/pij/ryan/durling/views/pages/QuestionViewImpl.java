package pij.ryan.durling.views.pages;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class QuestionViewImpl extends GridPane implements QuestionView {
    private TextArea questionArea;
    private TextField scoreArea;
    private final Button addQuestionButton;

    public QuestionViewImpl() {
        this.getStylesheets().add("styles/questionView.css");
        this.setId("questionView");
        this.setAlignment(Pos.BASELINE_CENTER);

        questionArea = getQuestionArea();
        this.add(questionArea, 1, 0);
        scoreArea = getScoreArea();
        this.add(scoreArea, 1, 1);
        addQuestionButton = addQuestionButton();
        this.add(addQuestionButton, 2, 1);
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
