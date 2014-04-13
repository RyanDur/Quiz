package gui.pij.ryan.durling.views;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.views.pages.QuestionView;
import pij.ryan.durling.views.pages.QuestionViewImpl;
import pij.ryan.durling.views.pages.Views;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class QuestionViewTest extends GuiTest {
    private QuestionView questionView;
    private Views views;

    @Override
    protected Parent getRootNode() {
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
