package pij.ryan.durling.views.pages;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;


public class Home extends BorderPane {

    private QuizCreator quizCreator;
    private HBox header;
    private GridPane body;
    private TextField createQuiz;
    private TextArea questionArea;
    private GridPane innerGrid;
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
    private HBox footer;
    private Label question;
    private Button save;

    public Home(QuizCreator quizCreator) {
        this.getStylesheets().add("styles/home.css");
        this.quizCreator = quizCreator;
        this.setId("quizCreator");
        buildQuizEditor();
        addNewQuizView();
    }

    private void buildQuizEditor() {
        header = getHeader();
        this.setTop(header);
        body = addBody();
        this.setCenter(body);
        footer = getFooter();
        this.setBottom(footer);
    }

    private GridPane addBody() {
        GridPane grid = new GridPane();
        grid.setId("body");
        return grid;
    }

    private HBox getHeader() {
        HBox hBox = new HBox();
        hBox.setId("header");
        return hBox;
    }

    private HBox getFooter() {
        HBox hBox = new HBox();
        hBox.setId("footer");
        return hBox;
    }

    private void addNewQuizView() {
        addQuizButton = addQuizButton();
        header.getChildren().add(addQuizButton);
    }

    private void createNewQuizView() {
        Button createQuizButton = createQuizButton();
        createQuiz = addQuizField();

        header.getChildren().remove(addQuizButton);
        header.getChildren().add(createQuiz);
        header.getChildren().add(createQuizButton);
    }

    private void addQuestionView() {
        innerGrid = new GridPane();
        innerGrid.setId("innerGrid");
        innerGrid.setAlignment(Pos.BASELINE_CENTER);

        questionArea = addQuestionArea();
        scoreArea = addScoreArea();

        innerGrid.add(scoreArea, 1, 0);

        addQuestionButton = addQuestionButton(questionArea, scoreArea);

        body.add(innerGrid, 1, 2);
        innerGrid.add(addQuestionButton, 2, 0);
        body.add(questionArea, 1, 1);
    }

    private void removeAddQuestionView() {
        body.getChildren().remove(questionArea);
        innerGrid.getChildren().remove(scoreArea);
        innerGrid.getChildren().remove(addQuestionButton);
    }

    private void addAnswerView() {
        answerArea = addAnswerArea();
        question = new Label(quizCreator.getQuestion());

        radios = addRadioButtons();
        addAnswerButton = addAnswerButton(answerArea);
        addAnotherQuestionButton = addAnotherQuestionButton();

        innerGrid.add(addAnswerButton, 2, 0);
        innerGrid.add(addAnotherQuestionButton, 3, 0);
        innerGrid.add(radios, 1, 0);

        body.add(question, 1, 0);
        body.add(answerArea, 1, 1);
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

    private TextField addQuizField() {
        TextField addQuizField = new TextField();
        addQuizField.setId("create-quiz");

        return addQuizField;
    }

    private TextArea addQuestionArea() {
        TextArea questionArea = new TextArea();

        questionArea.setPromptText("Add Question");
        questionArea.setId("add-question");

        return questionArea;
    }

    private TextField addScoreArea() {
        TextField scoreArea = new TextField();
        scoreArea.setId("score");

        scoreArea.setPromptText("Add Score");

        return scoreArea;
    }

    private TextArea addAnswerArea() {
        TextArea answerArea = new TextArea();
        answerArea.setPromptText("Add Answer");
        answerArea.setId("add-answer");
        return answerArea;
    }

    private Button addQuizButton() {
        Button addQuizButton = getButton("Add Quiz", "add-quiz-button");
        addQuizButton.setOnAction(actionEvent -> createNewQuizView());
        return addQuizButton;
    }

    private Button createQuizButton() {
        Button createQuizButton = getButton("Create Quiz", "create-quiz-button");

        createQuizButton.setOnAction(actionEvent -> {
            quizCreator.createQuiz(createQuiz.getText());
            header.getChildren().removeAll(createQuiz, createQuizButton);
            setTitle();
            addQuestionView();
        });

        return createQuizButton;
    }

    private void setTitle() {
        Label title = new Label(quizCreator.getName());
        title.setId("title");
        header.getChildren().add(title);
    }

    private Button addAnswerButton(TextArea answerArea) {
        Button addAnswerButton = getButton("Add Answer", "add-answer-button");

        addAnswerButton.setOnAction(actionEvent -> {
            String userAnswer = answerArea.getText();

            try {
                quizCreator.addAnswer(userAnswer, answerValue);
                resetAddAnswerView();
            } catch (IllegalQuizCreationException e) {
                e.printStackTrace();
            }
        });

        return addAnswerButton;
    }

    private Button addQuestionButton(TextArea questionArea, TextField scoreArea) {
        Button addQuestionButton = getButton("Add Question", "add-question-button");

        addQuestionButton.setOnAction(actionEvent -> {
            String userQuestion = questionArea.getText();
            int userScore = Integer.parseInt(scoreArea.getText());

            try {
                quizCreator.addQuestion(userQuestion, userScore);
                removeAddQuestionView();
                addAnswerView();
            } catch (IllegalQuizCreationException e) {
                e.printStackTrace();
            }
        });

        return addQuestionButton;
    }

    private Button addAnotherQuestionButton() {
        Button addAnotherQuestionButton = getButton("Another Question", "add-another-question");

        addAnotherQuestionButton.setOnAction(actionEvent -> {
            if (!footer.getChildren().contains(save) && quizCreator.validQuiz()) {
                addSaveBar();
            }
            body.getChildren().remove(question);
            removeAddAnswerView();
            addQuestionView();
        });

        return addAnotherQuestionButton;
    }

    private void addSaveBar() {
        save = getButton("Save", "save");

        save.setOnAction(e -> {
            try {
                quizCreator.save();
            } catch (IllegalQuizCreationException | InvalidQuizException e1) {
                e1.printStackTrace();
            }
        });

        footer.getChildren().add(save);
    }

    private GridPane addRadioButtons() {
        correct = getRadioButton("Correct", "correct", true);
        incorrect = getRadioButton("Incorrect", "incorrect", false);

        GridPane radios = new GridPane();
        radios.add(correct, 1, 0);
        radios.add(incorrect, 2, 0);
        radios.setHgap(10);

        return radios;
    }

    private RadioButton getRadioButton(String title, String id, boolean value) {
        RadioButton radioButton = new RadioButton(title);
        radioButton.setId(id);
        radioButton.setOnAction(e -> {
            answerValue = value;
        });
        return radioButton;
    }

    private Button getButton(String label, String id) {
        Button button = new Button(label);
        button.setId(id);
        return button;
    }
}