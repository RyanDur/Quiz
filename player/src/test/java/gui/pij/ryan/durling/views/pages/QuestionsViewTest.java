package gui.pij.ryan.durling.views.pages;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.views.factories.AnswerViewFactory;
import pij.ryan.durling.views.pages.QuestionsView;
import pij.ryan.durling.views.pages.QuestionsViewImpl;

import java.util.HashSet;
import java.util.Set;

import static org.loadui.testfx.Assertions.assertNodeExists;
import static org.loadui.testfx.controls.Commons.hasText;
import static org.mockito.Matchers.anySet;
import static org.mockito.Mockito.*;

public class QuestionsViewTest extends GuiTest {

    private Question question1;
    private Question question2;
    private AnswerViewFactory answerViewFactory;

    @Override
    protected Parent getRootNode() {
        Set<Question> questions = getQuestions();
        GridPane answerView = new GridPane();
        when(answerViewFactory.getAnswerView(anySet())).thenReturn(answerView);
        QuestionsView questionsView = new QuestionsViewImpl(questions, answerViewFactory);
        return (Parent) questionsView;
    }

    @Test
    public void shouldBeAbleToSeeTheQuestionsWithAnswers() {
        assertNodeExists(hasText(question1.getQuestion()));
        assertNodeExists(hasText(question2.getQuestion()));
        verify(answerViewFactory, times(2)).getAnswerView(anySet());
    }

    private Set<Question> getQuestions() {
        Set<Question> questions = new HashSet<>();
        answerViewFactory = mock(AnswerViewFactory.class);
        question1 = mock(Question.class);
        when(question1.getQuestion()).thenReturn("Who is Kieth?");
        question2 = mock(Question.class);
        when((question2.getQuestion())).thenReturn("What is spam?");
        questions.add(question1);
        questions.add(question2);
        return questions;
    }
}
