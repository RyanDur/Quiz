package pij.ryan.durling.views.pages;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;


public class Editor extends BorderPane{

    private QuizView quizView;
    private Views views;
    private QuizCreator quizCreator;
    private int quizId;
    private Button lockQuizButton;
    private AnswerView answerView;
    private QuestionView questionView;

    public Editor(QuizCreator quizCreator, Views views) {
        this.views = views;
        this.quizCreator = quizCreator;
        quizView = views.getQuizView();
        addNewQuizView();
    }

    private Button lockQuiz() {
        Button lockQuizButton = getButton("Lock Quiz", "lock");
        lockQuizButton.setOnAction(e -> {
            lockQuizButton.setText("Quiz Locked");
            quizCreator.lockQuiz(quizId);
        });
        return lockQuizButton;
    }

    private void addNewQuizView() {
        quizView.getAddQuizButton();
    }

    private void createNewQuizView(GridPane body, HBox header, QuizCreator quizCreator) {
        TextField createQuiz = addQuizField();
        Button createQuizButton = createQuizButton(body, header, quizCreator, createQuiz);

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
        this.setCenter((Node) questionView);
    }

    private void remove(Node view) {
        this.remove(view);
    }

    private void addAnswerView() {
        answerView = views.getAnswerView();
        addAnswer();
        addAnotherQuestion();
        this.setCenter((Node) answerView);

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


    private Button getButton(String label, String id) {
        Button button = new Button(label);
        button.setId(id);
        return button;
    }
}