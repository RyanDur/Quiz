package gui.pij.ryan.durling.views;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.views.pages.AnswerView;
import pij.ryan.durling.views.pages.AnswerViewImpl;
import pij.ryan.durling.views.pages.Views;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AnswerViewImplTest extends GuiTest {

    private QuizCreator mockQuizCreator;
    private AnswerView answerView;
    private String answerArea = "#add-answer";
    private String answer = "answer";
    private String correct = "#correct";
    private String addAnswer = "#add-answer-button";
    private Views views;

    @Override
    protected Parent getRootNode() {
        mockQuizCreator = mock(QuizCreator.class);
        views = mock(Views.class);
        answerView = new AnswerViewImpl();
        answerView.setQuestionLabel("What is a goldfish?\n My Goldfish");
        return (Parent) answerView;
    }

    @Test
    public void shouldBeAbleToAddACorrectAnswerAndGetNewView() throws InterruptedException, IllegalQuizCreationException {
        answerView.getAddAnswerButton().setOnMousePressed(e -> {
            views.getAnswerView();
        });

        click(answerArea)
                .type(answer)
                .click(correct)
                .click(addAnswer);

        verify(mockQuizCreator).addAnswer(answer, true);
        verify(views).getAnswerView();
    }

    @Test
    public void shouldBeAbleToAddAnIncorrectAnswerAndGetNewView() throws InterruptedException, IllegalQuizCreationException {
        answerView.getAddAnswerButton().setOnMousePressed(e -> {
            views.getAnswerView();
        });

        String incorrect = "#incorrect";

        click(answerArea)
                .type(answer)
                .click(incorrect)
                .click(addAnswer);

        verify(mockQuizCreator).addAnswer(answer, false);
        verify(views).getAnswerView();
    }

    @Test
    public void shouldBeAbleToAddAnotherQuestionAndGetNewView() {
        answerView.getAddAnotherQuestionButton().setOnMousePressed(e -> {
            views.getQuestionView();
        });

        String addAnotherQuestion = "#add-another-question";

        click(answerArea)
                .type(answer)
                .click(correct)
                .click(addAnotherQuestion);

        verify(views).getQuestionView();
    }
}
