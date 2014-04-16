package gui.pij.ryan.durling.views.pages;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.views.pages.ViewPane;
import pij.ryan.durling.views.pages.ViewPaneImpl;

import static org.loadui.testfx.Assertions.assertNodeExists;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

public class ViewPaneTest extends GuiTest {

    private ViewPane viewPane;

    @Override
    protected Parent getRootNode() {
        viewPane = new ViewPaneImpl();
        return (Parent) viewPane;
    }

    @Test
    public void shouldBeAbleToSetAView() throws InterruptedException {
        Platform.setImplicitExit(false);
        String testNode = "test-box";
        String test = "Test";
        HBox hBox = new HBox();
        hBox.setId(testNode);
        Label label = new Label(test);
        hBox.getChildren().add(label);

        Platform.runLater(() -> viewPane.setView(hBox));

        assertNodeExists(hasText(test));
    }

    @Test
    public void shouldBeAbleToSetAHelpfulMessage() {
        String test = "Helpful Message";
        Platform.runLater(() -> viewPane.setMessage(test));

        verifyThat("#" + ViewMessages.ERROR_LABEL, hasText(test));
    }
}
