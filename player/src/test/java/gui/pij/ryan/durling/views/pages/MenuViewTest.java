package gui.pij.ryan.durling.views.pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.views.pages.MenuView;
import pij.ryan.durling.views.pages.MenuViewImpl;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MenuViewTest extends GuiTest {

    private String title;
    private MenuView menuView;
    private String value = "#" + ViewMessages.MENU_VIEW_BUTTON_ID + "2";
    private String query = "#" + ViewMessages.MENU_VIEW_BUTTON_ID + "3";

    @Override
    protected Parent getRootNode() {
        Set<QuizOption> quizSet = getQuizzes();

        menuView = new MenuViewImpl();
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.BOTTOM_CENTER);
        stackPane.getChildren().add((Node) menuView);
        stackPane.setPrefHeight(700);
        stackPane.setPrefWidth(600);
        stackPane.setPadding(new Insets(50));
        return stackPane;
    }
//  Runs fine in intellij, but fails on gradle

//    @Test
//    public void shouldHaveAListOfQuizzes() throws InterruptedException {
//        Platform.setImplicitExit(false);
//        Platform.runLater(() -> {
//            int y = 0;
//            for (QuizOption option : getQuizzes()) {
//                Button button = getButton(option.getQuizTitle(), y);
//
//                button.setOnAction(e -> {
//                    option.getQuizId();
//                });
//
//                menuView.addOption(button, y++);
//            }
//            verifyThat(value, hasText(title));
//        });
//
//    }
//
//    @Test
//    public void shouldBeAbleToChooseAQuiz() {
//        Platform.setImplicitExit(false);
//        Platform.runLater(() -> {
//            int y = 0;
//            for (QuizOption option : getQuizzes()) {
//                Button button = getButton(option.getQuizTitle(), y);
//
//                button.setOnAction(e -> {
//                    option.getQuizId();
//                });
//
//                menuView.addOption(button, y++);
//            }
//            click(query);
//        });
//    }

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
