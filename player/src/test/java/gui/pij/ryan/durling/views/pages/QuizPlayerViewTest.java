package gui.pij.ryan.durling.views.pages;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.controllers.Menu;
import pij.ryan.durling.controllers.QuizPlayer;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.views.pages.QuizPlayerView;
import pij.ryan.durling.views.pages.QuizPlayerViewImpl;
import pij.ryan.durling.views.pages.Views;
import pij.ryan.durling.views.pages.ViewsImpl;

import java.util.HashSet;
import java.util.Set;

import static org.loadui.testfx.Assertions.assertNodeExists;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuizPlayerViewTest extends GuiTest {

    private String nameField = "#" + ViewMessages.NAME_FIELD;
    private String name = "Keith";
    private String signInButton = ViewMessages.SIGN_IN_BUTTON;
    private String title;

    @Override
    protected Parent getRootNode() {
        Views views = new ViewsImpl();
        QuizPlayer quizPlayer = getQuizPlayer();



        QuizPlayerView quizPlayerView = new QuizPlayerViewImpl(quizPlayer, views);
        return (Parent) quizPlayerView;
    }

    @Test
    public void shouldBeAbleToAddAUsersName() {

        click(nameField)
                .type(name)
                .click(signInButton);

        verifyThat("#" + ViewMessages.NAME_TITLE_ID, hasText(name));
    }

    @Test
    public void shouldBeAbleToSeeAListOfQuizzesQuiz() throws InterruptedException {
        click(nameField)
                .type(name)
                .click(signInButton);

        assertNodeExists(title);
    }

    @Test
    public void shouldBeAbleToChooseAQuiz() {
        click(nameField)
                .type(name)
                .click(signInButton)
                .click(title);

        verifyThat("#" + ViewMessages.QUIZ_TITLE_ID, hasText(title));
    }

    private QuizPlayer getQuizPlayer() {
        QuizPlayer quizPlayer = mock(QuizPlayer.class);
        Menu mockMenu = getMenu();
        when(quizPlayer.getMenu()).thenReturn(mockMenu);
        return quizPlayer;
    }

    private Menu getMenu() {
        Menu mockMenu = mock(Menu.class);
        Set<QuizOption> quizSet = getQuizzes();
        when(mockMenu.getQuizzes()).thenReturn(quizSet);
        return mockMenu;
    }

    private Set<QuizOption> getQuizzes() {
        Set<QuizOption> quizSet = new HashSet<>();
        title = "Poo";
        QuizOption quizOption = mock(QuizOption.class);
        when(quizOption.getQuizTitle()).thenReturn(title);
        when(quizOption.getQuizId()).thenReturn(1);

        String title1 = "Chicken";
        QuizOption quizOption1 = mock(QuizOption.class);
        when(quizOption1.getQuizTitle()).thenReturn(title1);
        when(quizOption1.getQuizId()).thenReturn(1);

        String title2 = "Steak";
        QuizOption quizOption2 = mock(QuizOption.class);
        when(quizOption2.getQuizTitle()).thenReturn(title2);
        when(quizOption2.getQuizId()).thenReturn(1);

        String title3 = "Fish";
        QuizOption quizOption3 = mock(QuizOption.class);
        when(quizOption3.getQuizTitle()).thenReturn(title3);
        when(quizOption3.getQuizId()).thenReturn(1);

        quizSet.add(quizOption);
        quizSet.add(quizOption1);
        quizSet.add(quizOption2);
        quizSet.add(quizOption3);
        return quizSet;
    }
}
