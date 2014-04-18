package pij.ryan.durling.views.navigation;

import com.google.inject.Inject;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.views.elements.Footer;
import pij.ryan.durling.views.elements.Header;
import pij.ryan.durling.views.elements.ViewBox;
import pij.ryan.durling.views.factories.Views;
import pij.ryan.durling.views.pages.*;


public class EditorImpl extends BorderPane implements Editor {

    private Views views;
    private QuizCreator quizCreator;
    private Answers answers;
    private Questions questions;
    private Footer footer;
    private Header header;
    private ViewBox viewBox;

    @Inject
    public EditorImpl(QuizCreator quizCreator, Views views) {
        this.getStylesheets().add(ViewMessages.EDITOR_VIEW_STYLE_SHEET);
        this.setId(ViewMessages.EDITOR_VIEW_ID);
        this.views = views;
        this.quizCreator = quizCreator;
        viewBox = views.getErrorBox();
        addHeader();
        this.setCenter((Node) viewBox);
        addFooter();
    }

    private void addHeader() {
        header = views.getHeader();
        header.getCreateQuizButton().setOnMousePressed(e -> {
            try {
                quizCreator.createQuiz(header.getTitle());
                viewBox.removeMessage();
                header.setTitle(quizCreator.getName());
                addQuestionView();
            } catch (IllegalArgumentException e1) {
                viewBox.setMessage(e1.getMessage());
            }
        });
        this.setTop((Node) header);
    }

    private void addFooter() {
        footer = views.getFooter();
        footer.getSaveButton().setOnAction(e -> {
            try {
                quizCreator.save();
                viewBox.removeMessage();
                resetEditor();
            } catch (IllegalQuizCreationException | InvalidQuizException e1) {
                viewBox.setMessage(e1.getMessage());
            }
        });
        this.setBottom((Node) footer);
    }


    private void addQuestionView() {
        questions = views.getQuestionView();
        questions.getAddQuestionButton().setOnAction(e -> {
            try {
                quizCreator.addQuestion(questions.getQuestion(), questions.getScore());
                viewBox.removeMessage();
                viewBox.removeView();
                addAnswerView();
            } catch (IllegalQuizCreationException | IllegalArgumentException e1) {
                viewBox.setMessage(e1.getMessage());
            }
        });
        viewBox.setView((Node) questions);
    }

    private void addAnswerView() {
        answers = views.getAnswerView();
        answers.setQuestionLabel(questions.getQuestion());
        viewBox.setView((Node) answers);
        addAnswer();
        addAnotherQuestion();

    }

    private void addAnotherQuestion() {
        answers.getAddAnotherQuestionButton().setOnMousePressed(e -> {
            viewBox.removeView();
            addQuestionView();
            if (quizCreator.validQuiz()) {
                footer.addSaveButton();
            }
        });
    }

    private void addAnswer() {
        answers.getAddAnswerButton().setOnAction(e -> {
            try {
                quizCreator.addAnswer(answers.getAnswer(), answers.getAnswerValue());
                viewBox.removeMessage();
                viewBox.removeView();
                addAnswerView();
            } catch (IllegalQuizCreationException | IllegalArgumentException e1) {
                viewBox.setMessage(e1.getMessage());
            }
        });
    }

    private void resetEditor() {
        viewBox.removeMessage();
        viewBox.removeView();
        addHeader();
        addFooter();
    }
}