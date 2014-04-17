package gui.pij.ryan.durling.views.pages;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.views.pages.Questions;
import pij.ryan.durling.views.pages.QuestionsImpl;
import pij.ryan.durling.views.factories.Views;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class QuestionsTest extends GuiTest {
    private Questions questions;
    private Views views;

    @Override
    protected Parent getRootNode() {
        views = mock(Views.class);
        questions = new QuestionsImpl();

        return (Parent) questions;
    }

    @Test
    public void shouldBeAbleToAddAQuestionAndChangeView() throws IllegalQuizCreationException {
        questions.getAddQuestionButton().setOnMousePressed(e -> {
            views.getAnswerView();
        });

        String bacon = "Bacon";
        String text = "9";
        String addQuestion = "#" + ViewMessages.ADD_QUESTION_BUTTON_ID;
        String addQuestionArea = "#" + ViewMessages.QUESTION_INPUT_AREA_ID;
        String scoreField = "#" + ViewMessages.SCORE_ID;

        click(addQuestionArea)
                .type(bacon)
                .click(scoreField)
                .type(text)
                .click(addQuestion);

        verify(views).getAnswerView();
    }
}
