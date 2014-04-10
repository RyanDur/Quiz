package gui.pij.ryan.durling.views;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.views.pages.AnswerView;
import pij.ryan.durling.views.pages.AnswerViewImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AnswerViewImplTest extends GuiTest {

    private QuizCreator mockQuizCreator;
    private AnswerView answerView;
    private String answerArea = "#add-answer";
    private String answer = "answer";
    private String correct = "#correct";
    private String addAnswer = "#add-answer-button";

    @Override
    protected Parent getRootNode() {
        mockQuizCreator = mock(QuizCreator.class);
        answerView = new AnswerViewImpl();
        return (Parent) answerView;
    }

    @Test
    public void shouldBeAbleToAddACorrectAnswer() throws InterruptedException, IllegalQuizCreationException {
        answerView.getAddAnswerButton().setOnAction(e -> {
            try {
                mockQuizCreator.addAnswer(anyString(), anyBoolean());
            } catch (IllegalQuizCreationException e1) {
                e1.printStackTrace();
            }
        });

        click(answerArea)
                .type(answer)
                .click(correct)
                .click(addAnswer);

        assertThat(true, is(equalTo(answerView.getAnswerValue())));
        assertThat(answer, is(equalTo(answerView.getAnswer())));
    }

    @Test
    public void shouldBeAbleToAddAnIncorrectAnswer() throws InterruptedException, IllegalQuizCreationException {
        answerView.getAddAnswerButton().setOnAction(e -> {
            try {
                mockQuizCreator.addAnswer(anyString(), anyBoolean());
            } catch (IllegalQuizCreationException e1) {
                e1.printStackTrace();
            }
        });

        String incorrect = "#incorrect";

        click(answerArea)
                .type(answer)
                .click(incorrect)
                .click(addAnswer);

        assertThat(false, is(equalTo(answerView.getAnswerValue())));
        assertThat(answer, is(equalTo(answerView.getAnswer())));
    }

    @Test
    public void shouldBeAbleToAddAnotherQuestion() {
        answerView.getAddAnotherQuestionButton().setOnAction(e -> {
            mockQuizCreator.getName();
        });

        String addAnotherQuestion = "#add-another-question";

        click(answerArea)
                .type(answer)
                .click(correct)
                .click(addAnotherQuestion);

        verify(mockQuizCreator).getName();
    }
}
