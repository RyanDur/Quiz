package pij.ryan.durling.views.pages;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;


public class Editor extends BorderPane {

    private Views views;
    private QuizCreator quizCreator;
    private HBox header;
    private GridPane body;
    private Button addQuizButton;
    private HBox footer;
    private int quizId;
    private Button lockQuizButton;
    private AnswerView answerView;
    private QuestionView questionView;
    private Button save;

    public Editor(QuizCreator quizCreator, Views views) {
        this.views = views;
        this.getStylesheets().add("styles/editor.css");
        this.quizCreator = quizCreator;
        this.setId("quizCreator");
        buildQuizEditor();
        addNewQuizView(body, header, quizCreator);
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

    private void addNewQuizView(GridPane body, HBox header, QuizCreator quizCreator) {
        addQuizButton = addQuizButton(body, header, quizCreator);
        header.getChildren().add(addQuizButton);
    }

    private void createNewQuizView(GridPane body, HBox header, QuizCreator quizCreator) {
        TextField createQuiz = addQuizField();
        Button createQuizButton = createQuizButton(body, header, quizCreator, createQuiz);

        header.getChildren().remove(addQuizButton);
        header.getChildren().add(createQuiz);
        header.getChildren().add(createQuizButton);
    }

    private TextField addQuizField() {
        TextField addQuizField = new TextField();
        addQuizField.setId("create-quiz");

        return addQuizField;
    }

    private Button addQuizButton(GridPane body, HBox header, QuizCreator quizCreator) {
        Button addQuizButton = getButton("Add Quiz", "add-quiz-button");
        addQuizButton.setOnAction(actionEvent -> createNewQuizView(body, header, quizCreator));
        return addQuizButton;
    }

    private Button createQuizButton(GridPane body, HBox header, QuizCreator quizCreator, TextField createQuiz) {
        Button createQuizButton = getButton("Create Quiz", "create-quiz-button");

        createQuizButton.setOnAction(actionEvent -> {
            lockQuizButton = lockQuiz();
            quizId = quizCreator.createQuiz(createQuiz.getText());
            header.getChildren().removeAll(createQuiz, createQuizButton);
            body.add(lockQuizButton, 2, 0);
            setTitle(header, quizCreator);

            addQuestionView();
        });

        return createQuizButton;
    }

    private void addQuestionView() {
        questionView = views.getQuestionView();
        questionView.getAddQuestionButton().setOnAction(e -> {
            addQuestion(quizCreator, questionView);
            remove((Node) questionView);
            addAnswerView();
        });
        body.add((Node) questionView, 1, 0);
    }

    private void remove(Node view) {
        body.getChildren().remove(view);
    }

    private void addAnswerView() {
        answerView = views.getAnswerView();
        addAnswer();
        addAnotherQuestion();
        body.add((Node) answerView, 1, 0);
        if (save == null && quizCreator.validQuiz()) {
            addSaveBar();
        }
    }

    private void addAnotherQuestion() {
        answerView.getAddAnotherQuestionButton().setOnAction(e -> {
            addQuestionView();
            remove((Node) answerView);
        });
    }

    private void addAnswer() {
        answerView.getAddAnswerButton().setOnAction(e -> {
            try {
                quizCreator.addAnswer(answerView.getAnswer(), answerView.getAnswerValue());
                remove((Node) answerView);
                addAnswerView();
            } catch (IllegalQuizCreationException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void addQuestion(QuizCreator quizCreator, QuestionView questionView) {
        try {
            quizCreator.addQuestion(questionView.getQuestion(), questionView.getScore());
        } catch (IllegalQuizCreationException e1) {
            e1.printStackTrace();
        }
    }

    private void setTitle(HBox header, QuizCreator quizCreator) {
        Label title = new Label(quizCreator.getName());
        title.setId("title");
        header.getChildren().add(title);
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

    private Button getButton(String label, String id) {
        Button button = new Button(label);
        button.setId(id);
        return button;
    }
}