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
    private HBox header;
    private GridPane body;
    private TextField createQuiz;
    private TextArea questionArea;
    private GridPane innerGrid;
    private Question question;
    private boolean answerValue;
    private TextArea answerArea;
    private Button addQuestionButton;
    private RadioButton correct;
    private RadioButton incorrect;
    private Button addAnotherQuestionButton;
    private GridPane radios;
    private Button addAnswerButton;
    private Button addQuizButton;
    private TextField scoreArea;

    public Home(QuizCreator quizCreator) {
        this.quizCreator = quizCreator;
        this.setPrefSize(600, 400);
        buildQuizEditor();
        addNewQuizView();
    }

    private void buildQuizEditor() {
        header = getHeader();
        this.setTop(header);
        body = addGrid();
        this.setCenter(body);
    }

    private GridPane addGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 12, 50, 12));
        return grid;
    }

    private HBox getHeader() {
        HBox hBox = new HBox();
        hBox.setId("header");
        hBox.setPadding(new Insets(15, 12, 15, 12));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #889499;");
        return hBox;
    }

    private void addNewQuizView() {
        addQuizButton = addQuizButton();
        header.getChildren().add(addQuizButton);
    }

    private void createNewQuizView() {
        Button createQuizButton = createQuizButton();
        addQuizField();
        createQuizButton();
        header.getChildren().remove(addQuizButton);
        header.getChildren().add(createQuizButton);
    }

    private void addQuestionView() {
        addQuestionArea();
        addScoreArea();
        addQuestionButton = addQuestionButton();
        innerGrid.add(addQuestionButton, 2, 0);
    }

    private void removeAddQuestionView() {
        body.getChildren().remove(questionArea);
        innerGrid.getChildren().remove(scoreArea);
        innerGrid.getChildren().remove(addQuestionButton);
    }

    private void addAnswerView() {
        addAnswerArea();
        radios = addRadioButtons();
        addAnswerButton = addAnswerButton();
        addAnotherQuestionButton = addAnotherQuestionButton();

        innerGrid.add(addAnswerButton, 2, 0);
        innerGrid.add(addAnotherQuestionButton, 3, 0);
        innerGrid.add(radios, 1, 0);
    }

    private void resetAddAnswerView() {
        answerArea.clear();
        incorrect.setSelected(false);
        correct.setSelected(false);
    }

    private void removeAddAnswerView() {
        body.getChildren().remove(answerArea);
        innerGrid.getChildren().remove(radios);
        innerGrid.getChildren().remove(addAnotherQuestionButton);
        innerGrid.getChildren().remove(addAnswerButton);
    }

    private void addQuizField() {
        createQuiz = new TextField();
        createQuiz.setId("create-quiz");

        header.getChildren().add(createQuiz);
    }

    private void addQuestionArea() {
        questionArea = new TextArea();

        questionArea.setPromptText("Add Question");
        questionArea.setId("add-question");
        body.add(questionArea, 1, 0);
    }

    private void addScoreArea() {
        innerGrid = new GridPane();
        innerGrid.setAlignment(Pos.BASELINE_CENTER);

        scoreArea = new TextField();
        scoreArea.setId("score");

        scoreArea.setPrefSize(200, 10);
        scoreArea.setPromptText("Add Score");
        innerGrid.add(scoreArea, 1, 0);
        innerGrid.setHgap(10);
        innerGrid.setVgap(10);

        body.add(innerGrid, 1, 1);
    }

    private void addAnswerArea() {
        answerArea = new TextArea();
        answerArea.setPromptText("Add Answer");
        answerArea.setId("add-answer");
        body.add(answerArea, 1, 0);
    }

    private Button addQuizButton() {
        Button addQuizButton = new Button();
        addQuizButton.setText("Add Quiz");
        addQuizButton.setPrefSize(100, 20);
        addQuizButton.setOnAction(actionEvent -> createNewQuizView());
        return addQuizButton;
    }

    private Button createQuizButton() {
        Button createQuizButton = new Button();
        createQuizButton.setText("Create Quiz");
        createQuizButton.setPrefSize(100, 20);

        createQuizButton.setOnAction(actionEvent -> {
            quizCreator.createQuiz(createQuiz.getText());
            Label title = new Label(quizCreator.getName());
            title.setId("title");
            header.getChildren().removeAll(createQuiz, createQuizButton);
            header.getChildren().add(title);
            addQuestionView();
        });

        return createQuizButton;
    }

    private Button addAnswerButton() {
        Button addAnswerButton = new Button("Add Answer");
        addAnswerButton.setId("add-answer-button");
        addAnswerButton.setPrefSize(105, 20);

        addAnswerButton.setOnAction(actionEvent -> {
            String userAnswer = answerArea.getText();
            boolean value = answerValue;

            try {
                Answer answer = quizCreator.createAnswer(userAnswer, value);
                quizCreator.addAnswer(question, answer);
                resetAddAnswerView();
            } catch (IllegalQuizCreationException e) {
                e.printStackTrace();
            }
        });

        return addAnswerButton;
    }

    private Button addQuestionButton() {
        Button addQuestionButton = new Button("Add Question");
        addQuestionButton.setId("add-question-button");
        addQuestionButton.setPrefSize(105, 20);

        addQuestionButton.setOnAction(actionEvent -> {
            String userQuestion = questionArea.getText();
            int userScore = Integer.parseInt(scoreArea.getText());

            try {
                question = quizCreator.createQuestion(userQuestion, userScore);
                quizCreator.addQuestion(question);
                removeAddQuestionView();
                addAnswerView();
            } catch (IllegalQuizCreationException e) {
                e.printStackTrace();
            }
        });

        return addQuestionButton;
    }

    private Button addAnotherQuestionButton() {
        addAnotherQuestionButton = new Button("Another Question");
        addAnotherQuestionButton.setId("add-another-question");
        addAnotherQuestionButton.setPrefSize(130, 20);

        addAnotherQuestionButton.setOnAction(actionEvent -> {
            removeAddAnswerView();
            addQuestionView();
        });

        return addAnotherQuestionButton;
    }

    private GridPane addRadioButtons() {
        correct = new RadioButton();
        incorrect = new RadioButton();
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

        return radios;
    }
}