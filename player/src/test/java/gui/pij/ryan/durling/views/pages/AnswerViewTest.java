package gui.pij.ryan.durling.views.pages;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.views.pages.AnswerView;
import pij.ryan.durling.views.pages.AnswerViewImpl;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AnswerViewTest extends GuiTest {

    private Answer answer4;

    @Override
    protected Parent getRootNode() {
        AnswerView answerView = new AnswerViewImpl(getAnswers());
        return (Parent) answerView;
    }

    @Test
    public void shouldBeAbleToChooseAnAnswer() {
        click("#" + ViewMessages.ANSWER_ID + "5");

        verify(answer4).select();
    }

    private Set<Answer> getAnswers() {
        Set<Answer> answers = new HashSet<>();

        Answer answer = mock(Answer.class);
        when(answer.getAnswer()).thenReturn("Answer 1");
        when(answer.getId()).thenReturn(1);

        Answer answer1 = mock(Answer.class);
        when(answer1.getAnswer()).thenReturn("Answer 2");
        when(answer1.getId()).thenReturn(2);

        Answer answer2 = mock(Answer.class);
        when(answer2.getAnswer()).thenReturn("Answer 3");
        when(answer2.getId()).thenReturn(3);

        Answer answer3 = mock(Answer.class);
        when(answer3.getAnswer()).thenReturn("Answer 4");
        when(answer3.getId()).thenReturn(4);

        answer4 = mock(Answer.class);
        when(answer4.getAnswer()).thenReturn("Answer 5");
        when(answer4.getId()).thenReturn(5);

        Answer answer5 = mock(Answer.class);
        when(answer5.getAnswer()).thenReturn("Answer 6");
        when(answer5.getId()).thenReturn(6);

        answers.add(answer);
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);
        answers.add(answer5);
        return answers;
    }
}
