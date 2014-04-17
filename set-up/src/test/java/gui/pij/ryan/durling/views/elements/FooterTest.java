package gui.pij.ryan.durling.views.elements;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.views.elements.Footer;
import pij.ryan.durling.views.elements.FooterImpl;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FooterTest extends GuiTest {

    private Footer footer;
    private QuizCreator quizCreator;

    @Override
    protected Parent getRootNode() {
        quizCreator = mock(QuizCreator.class);
        footer = new FooterImpl();
        footer.addSaveButton();
        return (Parent) footer;
    }

    @Test
    public void shouldBeAbleToAddASaveBar() throws InvalidQuizException, IllegalQuizCreationException {
        String saveButtonId = "#" + ViewMessages.SAVE_BUTTON_ID;

        footer.getSaveButton().setOnAction(e -> {
            try {
                quizCreator.save();
            } catch (IllegalQuizCreationException | InvalidQuizException e1) {
                e1.printStackTrace();
            }
        });

        click(saveButtonId);

        verifyThat(saveButtonId, hasText(ViewMessages.SAVE_BUTTON));
        verify(quizCreator).save();
    }
}
