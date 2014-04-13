package gui.pij.ryan.durling.views;

import javafx.application.Platform;
import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.views.pages.ErrorBox;
import pij.ryan.durling.views.pages.ErrorBoxImpl;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

public class ErrorBoxTest extends GuiTest {

    private ErrorBox errorBox;
    private String expected;
    private String errorLabel = "#" + ViewMessages.ERROR_LABEL_ID;

    @Override
    protected Parent getRootNode() {
        errorBox = new ErrorBoxImpl();
        expected = "Hello BOB";
        errorBox.setMessage(expected);
        return (Parent) errorBox;
    }

    @Test
    public void shouldBeAbleToSetAMessage() {
        verifyThat(errorLabel, hasText(expected));
    }

    @Test
    public void shouldBeAbleToRemoveAMessage() {
        Platform.runLater(() -> {
            errorBox.removeMessage();
            verifyThat(errorLabel, hasText(""));
        });
    }
}
