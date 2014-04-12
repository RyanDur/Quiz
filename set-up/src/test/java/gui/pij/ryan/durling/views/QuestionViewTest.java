package gui.pij.ryan.durling.views;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.views.pages.QuestionView;
import pij.ryan.durling.views.pages.QuestionViewImpl;
import pij.ryan.durling.views.pages.Views;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class QuestionViewTest extends GuiTest {
    private QuizCreator quizCreator;
    private QuestionView questionView;
    private Views views;

    @Override
    protected Parent getRootNode() {
        quizCreator = mock(QuizCreator.class);
        views = mock(Views.class);
        questionView = new QuestionViewImpl();

        return (Parent) questionView;
    }

    @Test
    public void shouldBeAbleToAddAQuestionAndChangeView() throws IllegalQuizCreationException {
        questionView.getAddQuestionButton().setOnMousePressed(e -> {
            views.getAnswerView();
        });

        String bacon = "Bacon";
        String text = "9";
        String addQuestion = "#add-question-button";
        String addQuestionArea = "#add-question-area";
        String scoreField = "#score";

        click(addQuestionArea)
                .type(bacon)
                .click(scoreField)
                .type(text)
                .click(addQuestion);

        verify(quizCreator).addQuestion(anyString(), anyInt());
        verify(views).getAnswerView();
    }
}
