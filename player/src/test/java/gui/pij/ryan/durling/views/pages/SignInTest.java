package gui.pij.ryan.durling.views.pages;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.views.pages.SignIn;
import pij.ryan.durling.views.pages.SignInImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

public class SignInTest extends GuiTest {

    private SignIn signIn;
    private String signInButton = "#" + ViewMessages.SIGN_IN_BUTTON_ID;

    @Override
    protected Parent getRootNode() {
        signIn = new SignInImpl();
        return (Parent) signIn;
    }

    @Test
    public void shouldHaveAPlaceToSignIn() {
        verifyThat(signInButton, hasText(ViewMessages.SIGN_IN_BUTTON));
    }

    @Test
    public void shouldBeAbleToGetNameFromUser() {
        String name = "Keith";
        click("#" + ViewMessages.NAME_FIELD)
                .type(name)
                .click(signInButton);

        assertThat(signIn.getName(), is(equalTo(name)));
    }
}
