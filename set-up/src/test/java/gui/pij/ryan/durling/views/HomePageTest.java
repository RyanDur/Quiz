package gui.pij.ryan.durling.views;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.views.pages.Home;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

public class HomePageTest extends GuiTest {

    private String text = "Add Quiz";

    @Override
    protected Parent getRootNode() {
        return new Home();
    }

    @Test
    public void shouldHaveAButton() {
        verifyThat(".button", hasText(text));
    }

    @Test
    public void shouldAskToCreateAQuizAfterClickingButton() {
        click(text);
        verifyThat(".button", hasText("Create Quiz"));
    }

    @Test
    public  void shouldBeAbleToAddANameOfAQuiz() {
        String name = "Name";
        String textField = ".text-field";
        click(text).click(textField).type(name);
        verifyThat(textField, hasText(name));
    }
}
