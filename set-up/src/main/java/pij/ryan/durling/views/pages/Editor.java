package pij.ryan.durling.views.pages;

import gui.pij.ryan.durling.views.Footer;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;


public class Editor extends BorderPane {

    private Views views;
    private QuizCreator quizCreator;
    private AnswerView answerView;
    private QuestionView questionView;
    private Footer footer;

    public Editor(QuizCreator quizCreator, Views views) {
        this.getStylesheets().add("styles/editor.css");
        this.setId("editor");
        this.views = views;
        this.quizCreator = quizCreator;
        addHeader();
        addFooter();
    }

    private void addFooter() {
        footer = views.getFooter();
        footer.getSaveButton().setOnAction(e -> {
            try {
                quizCreator.save();
            } catch (IllegalQuizCreationException | InvalidQuizException e1) {
                e1.printStackTrace();
            }
        });
        this.setBottom((Node) footer);
    }

    private void addHeader() {
        Header header = views.getHeader();
        header.getCreateQuizButton().setOnMousePressed(e -> {
            addLockQuizButton(header);
            quizCreator.createQuiz(header.getTitle());
            header.setTitle(quizCreator.getName());
            addQuestionView();
        });
        this.setTop((Node) header);
    }

    private void addLockQuizButton(Header quizView) {
        quizView.getLockQuizButton().setOnAction(event -> {
            quizCreator.lockQuiz(quizCreator.getQuizId());
            quizView.toggleLock();
        });
        quizView.setLockQuiz();
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
            if (quizCreator.validQuiz()) {
                footer.addSaveButton();
            }
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
}