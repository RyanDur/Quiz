package gui.pij.ryan.durling.views;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.views.pages.AnswerView;
import pij.ryan.durling.views.pages.QuestionViewImpl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class QuestionViewTest extends GuiTest {
    private String addQuestion = "#add-question-button";
    private String addQuestionArea = "#add-question";
    private String scoreField  = "#score";
    private QuizCreator quizCreator;
    private AnswerView answerView;

    @Override
    protected Parent getRootNode() {
        quizCreator = mock(QuizCreator.class);
        answerView = mock(AnswerView.class);
        return new QuestionViewImpl();
    }

    @Test
    public void should() throws IllegalQuizCreationException {
        String bacon = "Bacon";
        String text = "9";
        click(addQuestionArea)
                .type(bacon)
                .click(scoreField)
                .type(text)
                .click(addQuestion);

        verify(quizCreator).addQuestion(bacon, Integer.parseInt(text));
    }
}
