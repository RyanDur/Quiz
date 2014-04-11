package pij.ryan.durling.views.pages;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;


public class Editor extends BorderPane {

    private Views views;
    private QuizCreator quizCreator;
    private AnswerView answerView;
    private QuestionView questionView;

    public Editor(QuizCreator quizCreator, Views views) {
        this.getStylesheets().add("styles/editor.css");
        this.setId("editor");
        this.views = views;
        this.quizCreator = quizCreator;
        addNewQuizView();
    }

    private void addNewQuizView() {
        QuizView quizView = views.getQuizView(quizCreator);
        quizView.getCreateQuizButton().setOnMousePressed(e -> {
            //setLockQuiz();
            addQuestionView();
        });
        this.setTop((Node) quizView);
    }

    private void addQuestionView() {
        questionView = views.getQuestionView();
        questionView.getAddQuestionButton().setOnAction(e -> {
            try {
                quizCreator.addQuestion(questionView.getQuestion(), questionView.getScore());
                remove((Node) questionView);
                addAnswerView();
            } catch (IllegalQuizCreationException e1) {
                e1.printStackTrace();
            }
        });
        this.setCenter((Node) questionView);
    }

    private void addAnswerView() {
        answerView = views.getAnswerView();
        answerView.setQuestionLabel(questionView.getQuestion());
        addAnswer();
        addAnotherQuestion();
        this.setCenter((Node) answerView);
    }

    private void addAnotherQuestion() {
        answerView.getAddAnotherQuestionButton().setOnMousePressed(e -> {
            remove((Node) answerView);
            addQuestionView();
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

    private void remove(Node node) {
        this.getChildren().remove(node);
    }

    private Button getButton(String label, String id) {
        Button button = new Button(label);
        button.setId(id);
        return button;
    }
}