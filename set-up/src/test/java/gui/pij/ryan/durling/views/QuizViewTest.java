package gui.pij.ryan.durling.views;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.views.pages.QuizView;
import pij.ryan.durling.views.pages.QuizViewImpl;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuizViewTest extends GuiTest {

    private QuizView quizView;
    private QuizCreator mockQuizCreator;

    @Override
    protected Parent getRootNode() {
        mockQuizCreator = mock(QuizCreator.class);
        quizView = new QuizViewImpl();
        return (Parent) quizView;
    }

    @Test
    public void shouldBeAbleToAddAQuiz() {
        String title = "Poo";

        when(mockQuizCreator.getName()).thenReturn(title);

        quizView.getAddQuizButton().setOnAction(e -> {
            quizView.getQuizTitleField();
            quizView.getCreateQuizButton().setOnAction(event -> {
                mockQuizCreator.createQuiz(quizView.getTitle());
                quizView.setTitle(mockQuizCreator.getName());
            });
        });

        click("#add-quiz-button")
                .click("#create-quiz")
                .type(title)
                .click("#create-quiz-button");

        verify(mockQuizCreator).createQuiz(title);
        verifyThat("#title", hasText(title));
    }
}
