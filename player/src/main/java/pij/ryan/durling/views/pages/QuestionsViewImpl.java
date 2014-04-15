package pij.ryan.durling.views.pages;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.views.factories.AnswerViewFactory;

import java.util.Set;

public class QuestionsViewImpl extends ScrollPane implements QuestionsView {

    private Set<Question> questions;
    private AnswerViewFactory answerViewFactory;

    public QuestionsViewImpl(Set<Question> questions, AnswerViewFactory answerViewFactory) {
        this.questions = questions;
        this.answerViewFactory = answerViewFactory;
        this.getStylesheets().add(ViewMessages.QUESTION_VIEW_STYLE_SHEET);
        this.setId(ViewMessages.QUESTION_VIEW_ID);
        this.setContent(getQuestions());
    }

    public GridPane getQuestions() {
        GridPane gridPane = new GridPane();
        gridPane.setId(ViewMessages.QUESTIONS_ID);
        int y = 0;
        for (Question question : questions) {
            gridPane.add(getQuestion(y, question), 0, y);
            y++;
        }
        return gridPane;
    }

    private GridPane getQuestion(int y, Question question) {
        GridPane innerGrid = new GridPane();
        Label label = new Label();
        label.setText(question.getQuestion());
        innerGrid.setId(ViewMessages.QUESTION_ID + y);
        innerGrid.add(label, 0, 0);
        innerGrid.add(getAnswers(question), 0, 1);
        return innerGrid;
    }

    private GridPane getAnswers(Question question) {
        return answerViewFactory.getAnswerView(question.getAnswers());
    }
}
