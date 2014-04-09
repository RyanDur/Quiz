package pij.ryan.durling.views.pages;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;


public class Editor extends BorderPane {

    private QuizCreator quizCreator;
    private HBox header;
    private GridPane body;
    private boolean answerValue;
    private Button addQuizButton;
    private HBox footer;
    private Button save;
    private int quizId;
    private Button lockQuizButton;

    public Editor(QuizCreator quizCreator) {
        this.getStylesheets().add("styles/editor.css");
        this.quizCreator = quizCreator;
        this.setId("quizCreator");
        buildQuizEditor();
        addNewQuizView(body, header, quizCreator, footer, save);
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

    private Button lockQuiz() {
        Button lockQuizButton = getButton("Lock Quiz", "lock");
        lockQuizButton.setOnAction(e -> {
            lockQuizButton.setText("Quiz Locked");
            quizCreator.lockQuiz(quizId);
        });
        return lockQuizButton;
    }

    private void addNewQuizView(GridPane body, HBox header, QuizCreator quizCreator, HBox footer, Button save) {
        addQuizButton = addQuizButton(body, header, quizCreator, footer, save);
        header.getChildren().add(addQuizButton);
    }

    private void createNewQuizView(GridPane body, HBox header, QuizCreator quizCreator, HBox footer, Button save) {
        TextField createQuiz = addQuizField();
        Button createQuizButton = createQuizButton(body, header, quizCreator, createQuiz, footer, save);

        header.getChildren().remove(addQuizButton);
        header.getChildren().add(createQuiz);
        header.getChildren().add(createQuizButton);
    }

    private void addQuestionView(GridPane body, QuizCreator quizCreator, HBox footer, Button save) {
        GridPane innerGrid = new GridPane();
        innerGrid.setId("innerGrid");
        innerGrid.setAlignment(Pos.BASELINE_CENTER);

        TextArea questionArea = addQuestionArea();
        TextField scoreArea = addScoreArea();

        innerGrid.add(scoreArea, 1, 0);

        Button addQuestionButton = addQuestionButton(questionArea, scoreArea, body, innerGrid, quizCreator, footer, save);

        body.add(innerGrid, 1, 2);
        innerGrid.add(addQuestionButton, 2, 0);
        body.add(questionArea, 1, 1);
    }

    private void removeAddQuestionView(GridPane body, GridPane innerGrid, TextArea questionArea, TextField scoreArea, Button addQuestionButton) {
        body.getChildren().remove(questionArea);
        innerGrid.getChildren().remove(scoreArea);
        innerGrid.getChildren().remove(addQuestionButton);
    }

    private void addAnswerView(GridPane body, GridPane innerGrid, QuizCreator quizCreator, HBox footer, Button save) {
        TextArea answerArea = addAnswerArea();
        Label question = new Label(quizCreator.getQuestion());

        GridPane radios = addRadioButtons();
        Button addAnswerButton = addAnswerButton(answerArea, quizCreator, radios);
        Button addAnotherQuestionButton = addAnotherQuestionButton(body, innerGrid, question, footer, save, quizCreator, answerArea, radios, addAnswerButton);

        innerGrid.add(addAnswerButton, 2, 0);
        innerGrid.add(addAnotherQuestionButton, 3, 0);
        innerGrid.add(radios, 1, 0);

        body.add(question, 1, 0);
        body.add(answerArea, 1, 1);
    }

    private void resetAddAnswerView(TextArea answerArea, GridPane radios) {
        answerArea.clear();
        RadioButton radioButton = (RadioButton) radios.getChildren().get(0);
        RadioButton radioButton1 = (RadioButton) radios.getChildren().get(1);
        radioButton.setSelected(false);
        radioButton1.setSelected(false);
    }

    private void removeAddAnswerView(GridPane body, GridPane innerGrid, TextArea answerArea, GridPane radios, Button addAnotherQuestionButton, Button addAnswerButton) {
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

    private Button addQuizButton(GridPane body, HBox header, QuizCreator quizCreator, HBox footer, Button save) {
        Button addQuizButton = getButton("Add Quiz", "add-quiz-button");
        addQuizButton.setOnAction(actionEvent -> createNewQuizView(body, header, quizCreator, footer, save));
        return addQuizButton;
    }

    private Button createQuizButton(GridPane body, HBox header, QuizCreator quizCreator, TextField createQuiz, HBox footer, Button save) {
        Button createQuizButton = getButton("Create Quiz", "create-quiz-button");

        createQuizButton.setOnAction(actionEvent -> {
            lockQuizButton = lockQuiz();
            quizId = quizCreator.createQuiz(createQuiz.getText());
            header.getChildren().removeAll(createQuiz, createQuizButton);
            body.add(lockQuizButton, 2, 0);
            setTitle(header, quizCreator);
            addQuestionView(body, quizCreator, footer, save);
        });

        return createQuizButton;
    }

    private void setTitle(HBox header, QuizCreator quizCreator) {
        Label title = new Label(quizCreator.getName());
        title.setId("title");
        header.getChildren().add(title);
    }

    private Button addAnswerButton(TextArea answerArea, QuizCreator quizCreator, GridPane radios) {
        Button addAnswerButton = getButton("Add Answer", "add-answer-button");

        addAnswerButton.setOnAction(actionEvent -> {
            String userAnswer = answerArea.getText();

            try {
                quizCreator.addAnswer(userAnswer, answerValue);
                resetAddAnswerView(answerArea, radios);
            } catch (IllegalQuizCreationException e) {
                e.printStackTrace();
            }
        });

        return addAnswerButton;
    }

    private Button addQuestionButton(TextArea questionArea, TextField scoreArea, GridPane body, GridPane innerGrid, QuizCreator quizCreator, HBox footer, Button save) {
        Button addQuestionButton = getButton("Add Question", "add-question-button");

        addQuestionButton.setOnAction(actionEvent -> {
            String userQuestion = questionArea.getText();
            int userScore = Integer.parseInt(scoreArea.getText());

            try {
                quizCreator.addQuestion(userQuestion, userScore);
                removeAddQuestionView(body, innerGrid, questionArea, scoreArea, addQuestionButton);
                addAnswerView(body, innerGrid, quizCreator, footer, save);
            } catch (IllegalQuizCreationException e) {
                e.printStackTrace();
            }
        });

        return addQuestionButton;
    }

    private Button addAnotherQuestionButton(GridPane body, GridPane innerGrid, Label question, HBox footer, Button save, QuizCreator quizCreator, TextArea answerArea, GridPane radios, Button addAnswerButton) {
        Button addAnotherQuestionButton = getButton("Another Question", "add-another-question");

        addAnotherQuestionButton.setOnAction(actionEvent -> {
            if (!footer.getChildren().contains(save) && quizCreator.validQuiz()) {
                addSaveBar();
            }
            body.getChildren().remove(question);
            removeAddAnswerView(body, innerGrid, answerArea, radios, addAnotherQuestionButton, addAnswerButton);
            addQuestionView(body, quizCreator, footer, save);
        });

        return addAnotherQuestionButton;
    }

    private void addSaveBar() {
        save = getButton("Save", "save");

        save.setOnAction(e -> {
            try {
                quizCreator.save();
                body.getChildren().remove(lockQuizButton);
            } catch (IllegalQuizCreationException | InvalidQuizException e1) {
                e1.printStackTrace();
            }
        });

        footer.getChildren().add(save);
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
        radioButton.setOnAction(e -> setAnswerValue(value));
        return radioButton;
    }

    private Button getButton(String label, String id) {
        Button button = new Button(label);
        button.setId(id);
        return button;
    }

    private void setAnswerValue(boolean value) {
        answerValue = value;
    }
}