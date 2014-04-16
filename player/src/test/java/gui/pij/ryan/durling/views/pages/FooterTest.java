package gui.pij.ryan.durling.views.pages;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.views.pages.Footer;
import pij.ryan.durling.views.pages.FooterImpl;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

public class FooterTest extends GuiTest {

    private Footer footer;

    @Override
    protected Parent getRootNode() {
        footer = new FooterImpl();
        return (Parent) footer;
    }

    @Test
    public void shouldBeAbleToAddASubmitButton() {
        Platform.setImplicitExit(false);
        String submitButton = "#" + ViewMessages.SUBMIT_BUTTON_ID;
        Button button = new Button();
        button.setText("Hello");
        button.setAlignment(Pos.BOTTOM_RIGHT);

        Platform.runLater(() -> footer.setSubmitButton(button));

        verifyThat(submitButton, hasText("Hello"));
    }


}
