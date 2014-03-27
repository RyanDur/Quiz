package pij.ryan.durling.views.pages;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.models.Question;


public class Home extends BorderPane {

    private QuizCreator quizCreator;
    private HBox hBox;
    private Button addQuizButton;
    private GridPane grid;
    private TextField textField;
    private Button createQuizButton;
    private TextArea questionArea;

    public Home(QuizCreator quizCreator) {
        this.quizCreator = quizCreator;
        this.setPrefSize(600,400);
        this.setTop(header(createQuiz()));
        this.setCenter(addGrid());
    }

    private HBox header(Button button) {
        hBox = new HBox();
        hBox.setId("header");
        hBox.setPadding(new Insets(15, 12, 15, 12));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #889499;");

        hBox.getChildren().add(button);
        return hBox;
    }

    private Button createQuiz() {
        addQuizButton = new Button();
        addQuizButton.setText("Add Quiz");
        addQuizButton.setPrefSize(100, 20);
        addQuizButton.setOnAction(actionEvent -> addTextField());
        return addQuizButton;
    }

    private void addTextField() {
        textField = new TextField();
        createQuizButton = new Button();
        createQuizButton.setText("Create Quiz");
        createQuizButton.setPrefSize(100, 20);

        createQuizButton.setOnAction(actionEvent -> {
            hBox.getChildren().removeAll(textField, createQuizButton);
            Label title = new Label(quizCreator.getName());
            title.setId("title");
            hBox.getChildren().add(title);
            addQuestionCreator();
            addScoreArea();
        });

        hBox.getChildren().remove(addQuizButton);
        hBox.getChildren().add(createQuizButton);
        hBox.getChildren().add(textField);
    }

    private GridPane addGrid() {
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 12, 50, 12));

        return grid;
    }

    private void addQuestionCreator() {
        questionArea = new TextArea();

        questionArea.setPromptText("Add Question");
        questionArea.setId("add-question");
        grid.add(questionArea, 1, 0);
    }

    private void addScoreArea() {
        GridPane innerGrid = new GridPane();

        TextField scoreArea = new TextField();
        scoreArea.setId("score");

        scoreArea.setPrefSize(200, 10);
        scoreArea.setPromptText("Add Score");
        innerGrid.add(scoreArea, 1, 0);

        Button addQuestionButton = new Button("Add Question");
        addQuestionButton.setId("add-question-button");
        addQuestionButton.setPrefSize(100, 20);
        addQuestionButton.setOnAction(actionEvent -> {
            String userQuestion = questionArea.getText();
            int userScore = Integer.parseInt(scoreArea.getText());
            Question question = null;

            try {
                question = quizCreator.createQuestion(userQuestion, userScore);
                quizCreator.addQuestion(question);
            } catch (IllegalQuizCreationException e) {
                e.printStackTrace();
            }

        });
        innerGrid.add(addQuestionButton, 1, 1);

        grid.add(innerGrid, 1, 1);
    }
}