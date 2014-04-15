package gui.pij.ryan.durling.views.pages;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.views.pages.SignInView;
import pij.ryan.durling.views.pages.SignInViewImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

public class SignInViewTest extends GuiTest {

    private SignInView signInView;
    private String signInButton = "#" + ViewMessages.SIGN_IN_BUTTON_ID;

    @Override
    protected Parent getRootNode() {
        signInView = new SignInViewImpl();
        return (Parent) signInView;
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

        assertThat(signInView.getName(), is(equalTo(name)));
    }
}
