package gui.pij.ryan.durling.views.pages;

import javafx.scene.Parent;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.models.Question;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestionsViewTest extends GuiTest {

    private Question question1;
    private Question question2;

    @Override
    protected Parent getRootNode() {
//        Set<Question> questions = getQuestions();
//        answerViewFactory = mock(AnswerViewFactory.class);
//        GridPane answerView = new GridPane();
//        when(answerViewFactory.getAnswerView(anySet())).thenReturn(answerView);
//        QuestionView questionsView = new QuestionViewImpl(questions, answerViewFactory);
//
//        StackPane stackPane = new StackPane();
//        stackPane.setAlignment(Pos.BOTTOM_CENTER);
//        stackPane.getChildren().add((Node) questionsView);
//        stackPane.setPrefHeight(700);
//        stackPane.setPrefWidth(600);
//        stackPane.setPadding(new Insets(50));
        return null;
    }

//    @Test
//    public void shouldBeAbleToSeeTheQuestionsWithAnswers() {
//        Platform.setImplicitExit(false);
//        Platform.runLater(() -> {
//            assertNodeExists(hasText(question1.getQuestion()));
//            assertNodeExists(hasText(question2.getQuestion()));
//            verify(answerViewFactory, times(2)).getAnswerView(anySet());
//        });
//    }

    private Set<Question> getQuestions() {
        Set<Question> questions = new HashSet<>();
        question1 = mock(Question.class);
        when(question1.getQuestion()).thenReturn("Who is Kieth?");
        question2 = mock(Question.class);
        when((question2.getQuestion())).thenReturn("What is spam?");
        questions.add(question1);
        questions.add(question2);
        return questions;
    }
}
