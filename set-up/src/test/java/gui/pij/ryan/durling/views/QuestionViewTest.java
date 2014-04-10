package gui.pij.ryan.durling.views;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.views.pages.QuestionView;
import pij.ryan.durling.views.pages.QuestionViewImpl;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class QuestionViewTest extends GuiTest {
    private QuizCreator quizCreator;
    private QuestionView questionView;

    @Override
    protected Parent getRootNode() {
        quizCreator = mock(QuizCreator.class);
        questionView = new QuestionViewImpl();
        return (Parent) questionView;
    }

    @Test
    public void should() throws IllegalQuizCreationException {
        questionView.getAddQuestionButton().setOnAction(e -> {
            try {
                quizCreator.addQuestion(anyString(), anyInt());
            } catch (IllegalQuizCreationException e1) {
                e1.printStackTrace();
            }
        });

        String bacon = "Bacon";
        String text = "9";
        String addQuestion = "#add-question-button";
        String addQuestionArea = "#add-question";
        String scoreField = "#score";
        click(addQuestionArea)
                .type(bacon)
                .click(scoreField)
                .type(text)
                .click(addQuestion);

        verify(quizCreator).addQuestion(anyString(), anyInt());
    }
}
