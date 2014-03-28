package pij.ryan.durling.views.pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;


public class Home extends BorderPane {

    private QuizCreator quizCreator;
    private HBox hBox;
    private Button addQuizButton;
    private GridPane grid;
    private TextField textField;
    private Button createQuizButton;
    private TextArea questionArea;
    private GridPane innerGrid;
    private Question question;
    private boolean answerValue;
    private TextArea answerArea;

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
        textField.setId("create-quiz");
        createQuizButton = new Button();
        createQuizButton.setText("Create Quiz");
        createQuizButton.setPrefSize(100, 20);

        createQuizButton.setOnAction(actionEvent -> {
            quizCreator.createQuiz(textField.getText());
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
        innerGrid = new GridPane();
        innerGrid.setAlignment(Pos.BASELINE_CENTER);

        TextField scoreArea = new TextField();
        scoreArea.setId("score");

        scoreArea.setPrefSize(200, 10);
        scoreArea.setPromptText("Add Score");
        innerGrid.add(scoreArea, 1, 0);
        innerGrid.setHgap(10);
        innerGrid.setVgap(10);

        Button addQuestionButton = new Button("Add Question");
        addQuestionButton.setId("add-question-button");
        addQuestionButton.setPrefSize(105, 20);

        addQuestionButton.setOnAction(actionEvent -> {
            String userQuestion = questionArea.getText();
            int userScore = Integer.parseInt(scoreArea.getText());

            try {
                question = quizCreator.createQuestion(userQuestion, userScore);
                quizCreator.addQuestion(question);
                grid.getChildren().remove(questionArea);
                innerGrid.getChildren().remove(scoreArea);
                innerGrid.getChildren().remove(addQuestionButton);
                addAnswerCreator();
                addRadioButtons();
                addAddAnswerButton();
            } catch (IllegalQuizCreationException e) {
                e.printStackTrace();
            }

        });
        innerGrid.add(addQuestionButton, 2, 0);

        grid.add(innerGrid, 1, 1);
    }

    private void addAddAnswerButton() {
        Button addAnswerButton = new Button("Add Answer");
        addAnswerButton.setId("add-answer-button");
        addAnswerButton.setPrefSize(105, 20);

        addAnswerButton.setOnAction(actionEvent -> {
            String userAnswer = answerArea.getText();
            boolean value = answerValue;

            try {
                Answer answer = quizCreator.createAnswer(userAnswer, value);
                quizCreator.addAnswer(question, answer);
            } catch (IllegalQuizCreationException e) {
                e.printStackTrace();
            }
        });

        innerGrid.add(addAnswerButton, 2, 0);
    }

    private void addRadioButtons() {
        RadioButton correct = new RadioButton();
        RadioButton incorrect = new RadioButton();
        correct.setText("Correct");
        incorrect.setText("Incorrect");

        correct.setId("correct");
        incorrect.setId("incorrect");

        correct.setOnAction(actionEvent -> {
            answerValue = true;
        });

        incorrect.setOnAction(actionEvent -> {
            answerValue = false;
        });

        GridPane radios = new GridPane();
        radios.add(correct, 1, 0);
        radios.add(incorrect, 2, 0);
        radios.setHgap(10);

        innerGrid.add(radios, 1, 0);
    }

    private void addAnswerCreator() {
        answerArea = new TextArea();
        answerArea.setPromptText("Add Answer");
        answerArea.setId("add-answer");
        grid.add(answerArea, 1, 0);
    }
}