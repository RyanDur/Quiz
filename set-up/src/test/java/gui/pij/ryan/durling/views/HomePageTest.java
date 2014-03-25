package gui.pij.ryan.durling.views;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.views.pages.Home;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

public class HomePageTest extends GuiTest {

    @Override
    protected Parent getRootNode() {
        return new Home().getPage();
    }

    @Test
    public void shouldHaveAButton() {
        String text = "Add Quiz";
        verifyThat(".button", hasText(text));
    }
}
