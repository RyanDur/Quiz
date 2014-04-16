package gui.pij.ryan.durling.views.pages;

import javafx.application.Platform;
import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.views.pages.Header;
import pij.ryan.durling.views.pages.HeaderImpl;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

public class HeaderTest extends GuiTest {

    private Header header;

    @Override
    protected Parent getRootNode() {
        header = new HeaderImpl();
        return (Parent) header;
    }

    @Test
    public void shouldBeAbleToSetTheNameOfThePlayer() {
        Platform.setImplicitExit(false);
        String name = "David North";
        Platform.runLater(() -> header.setPlayerName(name));
        verifyThat("#" + ViewMessages.NAME_TITLE_ID, hasText(name));
    }

    @Test
    public void shouldBeAbleToSetTheTitleOfTheQuiz() {
        Platform.setImplicitExit(false);
        String name = "David Norths Plague";
        Platform.runLater(() -> header.setQuizTitle(name));
        verifyThat("#" + ViewMessages.QUIZ_TITLE_ID, hasText(name));
    }
}
