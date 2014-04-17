package gui.pij.ryan.durling.views.pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.views.pages.Answer;
import pij.ryan.durling.views.pages.AnswerImpl;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnswerTest extends GuiTest {

    private pij.ryan.durling.models.Answer answer4;
    private String query = "#" + ViewMessages.ANSWER_ID + "5";

    @Override
    protected Parent getRootNode() {
        Answer answer = new AnswerImpl();

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.BOTTOM_CENTER);
        stackPane.getChildren().add((Node) answer);
        stackPane.setPrefHeight(700);
        stackPane.setPrefWidth(600);
        stackPane.setPadding(new Insets(50));
        return stackPane;
    }

//    This style of test doesn't work in gradle, but does in intellij, is covered in integration tests

//    @Test
//    public void shouldBeAbleToChooseAnAnswer() {
//
//    }

    private Set<pij.ryan.durling.models.Answer> getAnswers() {
        Set<pij.ryan.durling.models.Answer> answers = new HashSet<>();

        pij.ryan.durling.models.Answer answer = mock(pij.ryan.durling.models.Answer.class);
        when(answer.getAnswer()).thenReturn("Answer 1");

        pij.ryan.durling.models.Answer answer1 = mock(pij.ryan.durling.models.Answer.class);
        when(answer1.getAnswer()).thenReturn("Answer 2");


        pij.ryan.durling.models.Answer answer2 = mock(pij.ryan.durling.models.Answer.class);
        when(answer2.getAnswer()).thenReturn("Answer 3");

        pij.ryan.durling.models.Answer answer3 = mock(pij.ryan.durling.models.Answer.class);
        when(answer3.getAnswer()).thenReturn("Answer 4");

        answer4 = mock(pij.ryan.durling.models.Answer.class);
        when(answer4.getAnswer()).thenReturn("Answer 5");

        pij.ryan.durling.models.Answer answer5 = mock(pij.ryan.durling.models.Answer.class);
        when(answer5.getAnswer()).thenReturn("Answer 6");

        answers.add(answer);
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);
        answers.add(answer5);
        return answers;
    }
}
