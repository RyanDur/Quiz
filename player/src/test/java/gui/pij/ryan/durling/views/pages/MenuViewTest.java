package gui.pij.ryan.durling.views.pages;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.controllers.Menu;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.views.pages.MenuView;
import pij.ryan.durling.views.pages.MenuViewImpl;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MenuViewTest extends GuiTest {

    private String title;
    private MenuView menu;

    @Override
    protected Parent getRootNode() {
        Menu mockMenu = mock(Menu.class);
        Set<QuizOption> quizSet = getQuizzes();
        when(mockMenu.getQuizzes()).thenReturn(quizSet);
        menu = new MenuViewImpl(mockMenu);
        return (Parent) menu;
    }

    @Test
    public void shouldHaveAListOfQuizzes() throws InterruptedException {
       verifyThat("#"+ ViewMessages.MENU_VIEW_BUTTON_ID +"2", hasText(title));
    }

    @Test
    public void shouldBeAbleToChooseAQuiz() {
        click("#"+ ViewMessages.MENU_VIEW_BUTTON_ID +"3");
        assertThat(menu.getChosenQuizId(), is(equalTo(1)));
    }

    private Set<QuizOption> getQuizzes() {
        Set<QuizOption> quizSet = new HashSet<>();
        title = "Poo";
        QuizOption quizOption = mock(QuizOption.class);
        when(quizOption.getQuizTitle()).thenReturn(title);
        when(quizOption.getQuizId()).thenReturn(1);

        QuizOption quizOption1 = mock(QuizOption.class);
        when(quizOption1.getQuizTitle()).thenReturn(title);
        when(quizOption1.getQuizId()).thenReturn(1);

        QuizOption quizOption2 = mock(QuizOption.class);
        when(quizOption2.getQuizTitle()).thenReturn(title);
        when(quizOption2.getQuizId()).thenReturn(1);

        QuizOption quizOption3 = mock(QuizOption.class);
        when(quizOption3.getQuizTitle()).thenReturn(title);
        when(quizOption3.getQuizId()).thenReturn(1);

        quizSet.add(quizOption);
        quizSet.add(quizOption1);
        quizSet.add(quizOption2);
        quizSet.add(quizOption3);
        return quizSet;
    }
}
