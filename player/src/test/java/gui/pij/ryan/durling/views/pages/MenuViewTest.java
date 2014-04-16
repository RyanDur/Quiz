package gui.pij.ryan.durling.views.pages;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.controllers.Menu;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.views.pages.MenuView;
import pij.ryan.durling.views.pages.MenuViewImpl;

import java.util.HashSet;
import java.util.Set;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MenuViewTest extends GuiTest {

    private String title;
    private MenuView menuView;

    @Override
    protected Parent getRootNode() {
        Menu mockMenu = mock(Menu.class);
        Set<QuizOption> quizSet = getQuizzes();
        when(mockMenu.getQuizzes()).thenReturn(quizSet);
        menuView = new MenuViewImpl();
        return (Parent) menuView;
    }

    @Test
    public void shouldHaveAListOfQuizzes() throws InterruptedException {
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            int y = 0;
            for (QuizOption option : getQuizzes()) {
                Button button = getButton(option.getQuizTitle(), y);

                button.setOnAction(e -> {
                    option.getQuizId();
                });

                menuView.addOption(button, y++);
            }
            verifyThat("#"+ ViewMessages.MENU_VIEW_BUTTON_ID +"2", hasText(title));
        });

    }

    @Test
    public void shouldBeAbleToChooseAQuiz() {
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            int y = 0;
            for (QuizOption option : getQuizzes()) {
                Button button = getButton(option.getQuizTitle(), y);

                button.setOnAction(e -> {
                    option.getQuizId();
                });

                menuView.addOption(button, y++);
            }
            click("#"+ ViewMessages.MENU_VIEW_BUTTON_ID +"3");
        });
    }
    //    gui.pij.ryan.durling.views.pages.MenuViewTest > shouldBeAbleToChooseAQuiz FAILED
//    org.loadui.testfx.exceptions.NoNodesFoundException at MenuViewTest.java:70
//
//    gui.pij.ryan.durling.views.pages.MenuViewTest > shouldHaveAListOfQuizzes FAILED
//    org.loadui.testfx.exceptions.NoNodesFoundException at MenuViewTest.java:52
//
//    gui.pij.ryan.durling.views.pages.QuizPlayerViewTest > shouldKnowIfWinnerAfterSubmitting FAILED
//    org.loadui.testfx.exceptions.NoNodesFoundException at QuizPlayerViewTest.java:133
//
//    gui.pij.ryan.durling.views.pages.ViewPaneTest > shouldBeAbleToSetAView FAILED
//    org.loadui.testfx.exceptions.NoNodesFoundException at ViewPaneTest.java:40
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

    private Button getButton(String name, int id) {
        Button button = new Button();
        button.setText(name);
        button.setId(ViewMessages.MENU_VIEW_BUTTON_ID + id);
        return button;
    }
}
