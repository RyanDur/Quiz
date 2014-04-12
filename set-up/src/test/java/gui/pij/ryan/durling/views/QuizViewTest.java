package gui.pij.ryan.durling.views;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.views.pages.QuizView;
import pij.ryan.durling.views.pages.QuizViewImpl;
import pij.ryan.durling.views.pages.Views;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;
import static org.mockito.Mockito.*;

public class QuizViewTest extends GuiTest {

    private QuizView quizView;
    private QuizCreator mockQuizCreator;
    private String title = "Poo";
    private String addQuizButton = "#add-quiz-button";
    private String quizTitleField = "#create-quiz";
    private String createQuizButton = "#create-quiz-button";
    private Views mockViews;

    @Override
    protected Parent getRootNode() {
        mockQuizCreator = mock(QuizCreator.class);
        mockViews = mock(Views.class);
        when(mockQuizCreator.getName()).thenReturn(title);

        quizView = new QuizViewImpl();
        quizView.getCreateQuizButton().setOnMousePressed(e -> {
            mockViews.getQuestionView();
            quizView.setLockQuiz();
            mockQuizCreator.createQuiz(quizView.getTitle());
            quizView.setTitle(mockQuizCreator.getName());
        });
        quizView.getLockQuizButton().setOnAction(e -> quizView.toggleLock());

        return (Parent) quizView;
    }

    @Test
    public void shouldBeAbleToAddAQuiz() {
        click(addQuizButton)
                .click(quizTitleField)
                .type(title)
                .click(createQuizButton);

        verify(mockQuizCreator).createQuiz(title);
        verifyThat("#title", hasText(title));
    }

    @Test
    public void shouldGetAQuestionViewWhenCreatingANewQuiz() {
        click(addQuizButton)
                .click(quizTitleField)
                .type(title)
                .click(createQuizButton);

        verify(mockViews).getQuestionView();
    }

    @Test
    public void shouldBeAbleToLockAQuizAfterCreation() {

        click(addQuizButton)
                .click(quizTitleField)
                .type(title)
                .click(createQuizButton);

        verifyThat("#lock", hasText("Lock Quiz"));
    }

    @Test
    public void shouldKnowWhenTheQuizIsLocked() {
        click(addQuizButton)
                .click(quizTitleField)
                .type(title)
                .click(createQuizButton)
                .click("#lock");

        verifyThat("#lock", hasText("Quiz Locked"));
    }
}
