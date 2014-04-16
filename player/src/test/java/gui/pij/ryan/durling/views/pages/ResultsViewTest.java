package gui.pij.ryan.durling.views.pages;

import javafx.application.Platform;
import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.views.pages.ResultsView;
import pij.ryan.durling.views.pages.ResultsViewImpl;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

public class ResultsViewTest extends GuiTest{

    private ResultsView resultsView;

    @Override
    protected Parent getRootNode() {
        resultsView = new ResultsViewImpl();
        return (Parent) resultsView;
    }

    @Test
    public void shouldHaveANotificationOfWinner() {
        Platform.setImplicitExit(false);
        Platform.runLater(() -> resultsView.setResults(true, 53));

        verifyThat("#" + ViewMessages.RESULT_ID, hasText(ViewMessages.WINNER));
    }

    @Test
    public void shouldHaveANotificationOfLoser() {
        Platform.setImplicitExit(false);
        Platform.runLater(() -> resultsView.setResults(false, 53));

        verifyThat("#" + ViewMessages.RESULT_ID, hasText(ViewMessages.LOSER));
    }
}
