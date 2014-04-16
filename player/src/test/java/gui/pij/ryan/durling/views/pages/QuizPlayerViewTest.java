package gui.pij.ryan.durling.views.pages;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.controllers.QuizPlayer;
import pij.ryan.durling.views.pages.QuizPlayerView;
import pij.ryan.durling.views.pages.QuizPlayerViewImpl;
import pij.ryan.durling.views.pages.Views;

import static org.mockito.Mockito.mock;

public class QuizPlayerViewTest extends GuiTest {

    @Override
    protected Parent getRootNode() {
        QuizPlayer quizPlayer = mock(QuizPlayer.class);
        Views views = mock(Views.class);

        QuizPlayerView quizPlayerView = new QuizPlayerViewImpl(quizPlayer, views);
        return (Parent) quizPlayerView;
    }

    @Test
    public void shouldBeAbleToAddAUsersName() {
//        String name = "Keith";
//
//        click("#" + ViewMessages.NAME_FIELD)
//                .type(name)
//                .click(ViewMessages.SIGN_IN_BUTTON);
//
//        verifyThat("#" + ViewMessages.NAME_TITLE_ID, hasText(name));
    }
}
