package gui.pij.ryan.durling.views;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.views.pages.Home;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

public class HomePageTest extends GuiTest {

    private String text = "Add Quiz";
    private String name = "Name";
    private String textField = ".text-field";
    private String createQuiz = "Create Quiz";
    private String button = ".button";

    @Override
    protected Parent getRootNode() {
        return new Home();
    }

    @Test
    public void shouldHaveAButton() {

        verifyThat(button, hasText(text));
    }

    @Test
    public void shouldAskToCreateAQuizAfterClickingButton() {
        click(text);
        verifyThat(button, hasText(createQuiz));
    }

    @Test
    public  void shouldBeAbleToAddANameOfAQuiz() {
        click(text).click(textField).type(name);
        verifyThat(textField, hasText(name));
    }

    @Test
    public void shouldBeAbleToAddAQuestionAfterCreatingAQuiz() {
        click(text)
                .click(textField)
                .type(name)
                .click(createQuiz)
                .click("#add-question")
                .type("Hello");

        verifyThat("#add-question", hasText("Hello"));
    }
}
