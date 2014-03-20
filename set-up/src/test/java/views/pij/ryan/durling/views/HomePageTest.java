package views.pij.ryan.durling.views;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.views.HomePage;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

public class HomePageTest extends GuiTest {
    @Override
    protected Parent getRootNode() {
        return new HomePage();
    }

    @Test
    public void shouldHaveAButton() {
        verifyThat(".button", hasText("Add Quiz"));
    }
}
