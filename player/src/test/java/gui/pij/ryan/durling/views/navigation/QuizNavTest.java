package gui.pij.ryan.durling.views.navigation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.controllers.QuizPlayer;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.views.navigation.QuizNav;
import pij.ryan.durling.views.navigation.QuizNavImpl;
import pij.ryan.durling.views.factories.Views;
import pij.ryan.durling.views.factories.ViewsImpl;

import java.util.HashSet;
import java.util.Set;

import static org.loadui.testfx.Assertions.assertNodeExists;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuizNavTest extends GuiTest {

    private String nameField = "#" + ViewMessages.NAME_FIELD;
    private String name = "Keith";
    private String signInButton = ViewMessages.SIGN_IN_BUTTON;
    private String quizTitle = "Poo";
    private Question question1;
    private Question question2;
    private Answer answer4;
    private QuizPlayer quizPlayer;
    private String answer5 = "#" + ViewMessages.ANSWER_ID + 3;
    private String submit = ViewMessages.SUBMIT;
    private String playAgain = ViewMessages.PLAY_AGAIN;
    private Answer answer;
    private Answer answer1;
    private Answer answer2;
    private Answer answer3;
    private Answer answer51;

    @Override
    protected Parent getRootNode() {
        Views views = new ViewsImpl();
        quizPlayer = mock(QuizPlayer.class);
        quizPlayer = getQuizPlayer();
        Set<Question> questions = getQuestions();
        when(quizPlayer.getQuestions()).thenReturn(questions);
        QuizNav quizNav = new QuizNavImpl(quizPlayer, views);

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.BOTTOM_CENTER);
        stackPane.getChildren().add((Node) quizNav);
        stackPane.setPrefHeight(800);
        stackPane.setPrefWidth(600);
        stackPane.setPadding(new Insets(50));
        return stackPane;
    }

    @Test
    public void shouldBeAbleToSeeAListOfQuizzesQuiz() throws InterruptedException {
        click(nameField)
                .type(name)
                .click(signInButton);

        assertNodeExists(quizTitle);
    }

    @Test
    public void shouldBeAbleToSeeTheAnswersForTheQuestionsOfTheChosenAQuiz() {
        click(nameField)
                .type(name)
                .click(signInButton)
                .click(quizTitle);

        assertNodeExists(ViewMessages.SUBMIT);
        assertNodeExists(hasText(question1.getQuestion()));
        assertNodeExists(hasText(question2.getQuestion()));
        assertNodeExists(hasText(answer4.getAnswer()));
        verifyThat("#" + ViewMessages.QUIZ_TITLE_ID, hasText(quizTitle));
    }

    @Test
    public void shouldKnowIfWinnerAfterSubmitting() {
        when(quizPlayer.hasWon()).thenReturn(true);
        when(quizPlayer.getScore()).thenReturn(54);
        when(answer.getValue()).thenReturn(true);
        when(answer1.getValue()).thenReturn(true);
        when(answer2.getValue()).thenReturn(true);
        when(answer3.getValue()).thenReturn(true);
        when(answer4.getValue()).thenReturn(true);
        when(answer4.getValue()).thenReturn(true);
        when(answer51.getValue()).thenReturn(true);

        click(nameField)
                .type(name)
                .click(signInButton)
                .click(quizTitle)
                .click(answer5)
                .click(submit);

        assertNodeExists(ViewMessages.WINNER);
        verify(quizPlayer).addScore(anyInt());
        verify(quizPlayer).submitQuiz();
    }

    @Test
    public void shouldKnowIfLoserAfterSubmitting() {
        when(quizPlayer.hasWon()).thenReturn(false);
        when(quizPlayer.getScore()).thenReturn(54);
        click(nameField)
                .type(name)
                .click(signInButton)
                .click(quizTitle)
                .click(answer5)
                .click(submit);

        assertNodeExists(ViewMessages.LOSER);
    }

    @Test
    public void shouldBeAbleToPlayAnotherQuizAfterPlaying() {
        when(quizPlayer.hasWon()).thenReturn(true);
        when(quizPlayer.getScore()).thenReturn(54);

        click(nameField)
                .type(name)
                .click(signInButton)
                .click(quizTitle)
                .click(answer5)
                .click(submit);

        assertNodeExists(ViewMessages.PLAY_AGAIN);
    }

    @Test
    public void shouldBeAbleToPlayAnotherQuiz() {
         click(nameField)
                .type(name)
                .click(signInButton)
                .click(quizTitle)
                .click(answer5)
                .click(submit)
                .click(playAgain);

        assertNodeExists(quizTitle);
    }

    private QuizPlayer getQuizPlayer() {
        Set<QuizOption> quizSet = getQuizzes();
        when(quizPlayer.getMenu()).thenReturn(quizSet);
        return quizPlayer;
    }

    private Set<QuizOption> getQuizzes() {
        Set<QuizOption> quizSet = new HashSet<>();
        QuizOption quizOption = mock(QuizOption.class);
        when(quizOption.getQuizTitle()).thenReturn(quizTitle);
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

    private Set<Question> getQuestions() {
        Set<Question> questions = new HashSet<>();
        Set<Answer> answers = getAnswers();
        question1 = mock(Question.class);
        when(question1.getQuestion()).thenReturn("Who is Kieth?");
        when(question1.getAnswers()).thenReturn(answers);

        question2 = mock(Question.class);
        when((question2.getQuestion())).thenReturn("What is spam?");
        when(question2.getAnswers()).thenReturn(answers);

        questions.add(question1);
        questions.add(question2);
        return questions;
    }

    private Set<Answer> getAnswers() {
        Set<Answer> answers = new HashSet<>();

        answer = mock(Answer.class);
        when(answer.getAnswer()).thenReturn("Answer 1");

        answer1 = mock(Answer.class);
        when(answer1.getAnswer()).thenReturn("Answer 2");

        answer2 = mock(Answer.class);
        when(answer2.getAnswer()).thenReturn("Answer 3");

        answer3 = mock(Answer.class);
        when(answer3.getAnswer()).thenReturn("Answer 4");

        answer4 = mock(Answer.class);
        when(answer4.getAnswer()).thenReturn("Answer 5");

        answer51 = mock(Answer.class);
        when(answer51.getAnswer()).thenReturn("Answer 6");

        answers.add(answer);
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);
        answers.add(answer51);
        return answers;
    }
}
