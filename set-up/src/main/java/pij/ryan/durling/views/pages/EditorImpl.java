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

    @Inject
    public EditorImpl(QuizCreator quizCreator, Views views) {
        this.getStylesheets().add(ViewMessages.EDITOR_VIEW_STYLE_SHEET);
        this.setId(ViewMessages.EDITOR_VIEW_ID);
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
                resetEditor();
            } catch (IllegalQuizCreationException | InvalidQuizException e1) {
                e1.printStackTrace();
            }
        });
        this.setBottom((Node) footer);
    }

    private void addHeader() {
        header = views.getHeader();
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

    private void resetEditor() {
        remove((Node) questionView);
        remove((Node) answerView);
        remove((Node) header);
        remove((Node) footer);
        addHeader();
        addFooter();
    }

    private void remove(Node node) {
        this.getChildren().remove(node);
    }
}