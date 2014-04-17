package gui.pij.ryan.durling.views.elements;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.views.elements.Header;
import pij.ryan.durling.views.elements.HeaderImpl;
import pij.ryan.durling.views.factories.Views;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;
import static org.mockito.Mockito.*;

public class HeaderTest extends GuiTest {

    private Header quizView;
    private QuizCreator mockQuizCreator;
    private String title = "Poo";
    private String addQuizButton = "#" + ViewMessages.ADD_QUIZ_BUTTON_ID;
    private String quizTitleField = "#" + ViewMessages.CREATE_QUIZ_TITLE_FIELD_ID;
    private String createQuizButton = "#" + ViewMessages.CREATE_QUIZ_BUTTON_ID;
    private Views mockViews;
    private String lock = "#" + ViewMessages.LOCK_QUIZ_BUTTON_ID;

    @Override
    protected Parent getRootNode() {
        mockQuizCreator = mock(QuizCreator.class);
        mockViews = mock(Views.class);
        when(mockQuizCreator.getName()).thenReturn(title);

        quizView = new HeaderImpl();
        quizView.getCreateQuizButton().setOnMousePressed(e -> {
            quizView.getLockQuizButton().setOnAction(event -> {
                mockQuizCreator.lockQuiz(0);
                quizView.toggleLock();
            });
            quizView.setLockQuiz();
            mockViews.getQuestionView();
            mockQuizCreator.createQuiz(quizView.getTitle());
            quizView.setTitle(mockQuizCreator.getName());
        });

        return (Parent) quizView;
    }

    @Test
    public void shouldBeAbleToAddAQuiz() {
        click(addQuizButton)
                .click(quizTitleField)
                .type(title)
                .click(createQuizButton);

        verify(mockQuizCreator).createQuiz(title);
        verifyThat("#" + ViewMessages.TITLE_ID, hasText(title));
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

        verifyThat(lock, hasText(ViewMessages.LOCK_QUIZ_BUTTON));
    }

    @Test
    public void shouldKnowWhenTheQuizIsLocked() {
        click(addQuizButton)
                .click(quizTitleField)
                .type(title)
                .click(createQuizButton)
                .click(lock);

        verifyThat(lock, hasText(ViewMessages.LOCKED_QUIZ_BUTTON));
        verify(mockQuizCreator).lockQuiz(anyInt());
    }
}
