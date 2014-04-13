package pij.ryan.durling.views.pages;

import com.google.inject.Inject;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;
import pij.ryan.durling.messages.ViewMessages;


public class EditorImpl extends BorderPane implements Editor {

    private Views views;
    private QuizCreator quizCreator;
    private AnswerView answerView;
    private QuestionView questionView;
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
                addLockQuizButton(header);
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
                quizCreator.unlockQuiz(quizCreator.getQuizId());
                viewBox.removeMessage();
                resetEditor();
            } catch (IllegalQuizCreationException | InvalidQuizException e1) {
                viewBox.setMessage(e1.getMessage());
            }
        });
        this.setBottom((Node) footer);
    }


    private void addQuestionView() {
        questionView = views.getQuestionView();
        questionView.getAddQuestionButton().setOnAction(e -> {
            try {
                quizCreator.addQuestion(questionView.getQuestion(), questionView.getScore());
                viewBox.removeMessage();
                viewBox.removeView();
                addAnswerView();
            } catch (IllegalQuizCreationException | IllegalArgumentException e1) {
                viewBox.setMessage(e1.getMessage());
            }
        });
        viewBox.setView((Node) questionView);
    }

    private void addAnswerView() {
        answerView = views.getAnswerView();
        answerView.setQuestionLabel(questionView.getQuestion());
        viewBox.setView((Node) answerView);
        addAnswer();
        addAnotherQuestion();

    }

    private void addAnotherQuestion() {
        answerView.getAddAnotherQuestionButton().setOnMousePressed(e -> {
            viewBox.removeView();
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
                viewBox.removeMessage();
                viewBox.removeView();
                addAnswerView();
            } catch (IllegalQuizCreationException | IllegalArgumentException e1) {
                viewBox.setMessage(e1.getMessage());
            }
        });
    }

    private void addLockQuizButton(Header quizView) {
        quizView.getLockQuizButton().setOnAction(event -> {
            if (quizView.getLockQuizButton().getText().equals(ViewMessages.LOCK_QUIZ_BUTTON)) {
                quizCreator.lockQuiz(quizCreator.getQuizId());
                quizView.toggleLock();
            } else {
                quizCreator.unlockQuiz(quizCreator.getQuizId());
                quizView.toggleLock();
            }
        });
        quizView.setLockQuiz();
    }

    private void resetEditor() {
        viewBox.removeMessage();
        viewBox.removeView();
        addHeader();
        addFooter();
    }

    private void remove(Node node) {
        this.getChildren().remove(node);
    }
}