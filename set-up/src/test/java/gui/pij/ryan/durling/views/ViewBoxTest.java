package gui.pij.ryan.durling.views;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.views.pages.ViewBox;
import pij.ryan.durling.views.pages.ViewBoxImpl;

import static org.loadui.testfx.Assertions.assertNodeExists;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

public class ViewBoxTest extends GuiTest {

    private ViewBox viewBox;
    private String expected = "Hello BOB";
    private String errorLabel = "#" + ViewMessages.ERROR_LABEL_ID;
    private Label label;

    @Override
    protected Parent getRootNode() {
        viewBox = new ViewBoxImpl();
        return (Parent) viewBox;
    }

    @Test
    public void shouldBeAbleToSetAMessage() {
        Platform.runLater(() -> viewBox.setMessage(expected));
        verifyThat(errorLabel, hasText(expected));

    }

    @Test
    public void shouldBeAbleToRemoveAMessage() {
        Platform.runLater(() -> {
            viewBox.setMessage(expected);
            viewBox.removeMessage();
        });
        verifyThat(errorLabel, hasText(""));
    }

    @Test
    public void shouldBeAbleToAddAView() {
        Platform.runLater(() -> {
            HBox borderPane = new HBox();
            borderPane.setId("expected");
            label = new Label();
            label.setText("expected\n\n\n\n\n\nMoney");
            borderPane.getChildren().add(label);
            viewBox.setView(borderPane);
        });

        assertNodeExists("#expected");
        verifyThat("#expected .label", hasText("expected\n\n\n\n\n\nMoney"));
    }

    @Test
    public void shouldBeAbleToRemoveAView() {
        Platform.runLater(() -> {
            HBox borderPane = new HBox();
            borderPane.setId("expected");
            label = new Label("expected\n\n\n\n\n\nMoney");

            borderPane.getChildren().add(label);
            viewBox.setView(borderPane);
            viewBox.removeView();
        });;
    }
}
