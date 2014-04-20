package gui.pij.ryan.durling.views.pages;

import javafx.application.Platform;
import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.views.pages.Results;
import pij.ryan.durling.views.pages.ResultsImpl;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

public class ResultsTest extends GuiTest{

    private Results results;

    @Override
    protected Parent getRootNode() {
        results = new ResultsImpl();
        return (Parent) results;
    }

    @Test
    public void shouldHaveANotificationOfWinner() {
        Platform.setImplicitExit(false);
        Platform.runLater(() -> results.setResults(true, 53, "Bob", 4));
        verifyThat("#" + ViewMessages.RESULT_ID, hasText(ViewMessages.WINNER));
    }

    @Test
    public void shouldHaveANotificationOfLoser() {
        Platform.setImplicitExit(false);
        Platform.runLater(() -> results.setResults(false, 53, "Bob", 400));
        verifyThat("#" + ViewMessages.RESULT_ID, hasText(ViewMessages.LOSER));
    }
}
