package gui.pij.ryan.durling.views.pages;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.views.pages.Answers;
import pij.ryan.durling.views.pages.AnswersImpl;
import pij.ryan.durling.views.factories.Views;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AnswersImplTest extends GuiTest {

    private Answers answers;
    private String answerArea = "#" + ViewMessages.ANSWER_AREA_ID;
    private String answer = "answer";
    private String correct = "#" + ViewMessages.CORRECT_ID;
    private Views views;

    @Override
    protected Parent getRootNode() {
        views = mock(Views.class);
        answers = new AnswersImpl();
        answers.setQuestionLabel("What is a goldfish?\n My Goldfish");
        return (Parent) answers;
    }

    @Test
    public void shouldBeAbleToAddACorrectAnswerAndGetNewView() throws InterruptedException, IllegalQuizCreationException {
        answers.getAddAnswerButton().setOnMousePressed(e -> {
            views.getAnswerView();
        });

        String addAnswer = "#" + ViewMessages.ANSWER_BUTTON_ID;
        click(answerArea)
                .type(answer)
                .click(correct)
                .click(addAnswer);

        verify(views).getAnswerView();
    }

    @Test
    public void shouldBeAbleToAddAnotherQuestionAndGetNewView() {
        answers.getAddAnotherQuestionButton().setOnMousePressed(e -> {
            views.getQuestionView();
        });

        String addAnotherQuestion = "#" + ViewMessages.ANOTHER_QUESTION_BUTTON_ID;

        click(answerArea)
                .type(answer)
                .click(correct)
                .click(addAnotherQuestion);

        verify(views).getQuestionView();
    }
}
