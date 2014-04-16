package gui.pij.ryan.durling.views.pages;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.controllers.Menu;
import pij.ryan.durling.controllers.QuizPlayer;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
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
    private String quizTitle = "Poo";
    private Question question1;
    private Question question2;
    private Answer answer4;
    private QuizPlayer quizPlayer;
    private String answer5 = "#" + ViewMessages.ANSWER_ID + 4;
    private String submit = ViewMessages.SUBMIT;

    @Override
    protected Parent getRootNode() {
        Views views = new ViewsImpl();
        quizPlayer = getQuizPlayer();
        Set<Question> questions = getQuestions();
        when(quizPlayer.getQuestions()).thenReturn(questions);
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

        assertNodeExists(quizTitle);
    }

    @Test
    public void shouldBeAbleToChooseAQuiz() {
        click(nameField)
                .type(name)
                .click(signInButton)
                .click(quizTitle);

        verifyThat("#" + ViewMessages.QUIZ_TITLE_ID, hasText(quizTitle));
    }

    @Test
    public void shouldBeAbleToSeeTheQuestionsOfTheChosenAQuiz() {
        click(nameField)
                .type(name)
                .click(signInButton)
                .click(quizTitle);

        assertNodeExists(hasText(question1.getQuestion()));
        assertNodeExists(hasText(question2.getQuestion()));
        verifyThat("#" + ViewMessages.QUIZ_TITLE_ID, hasText(quizTitle));
    }

    @Test
    public void shouldBeAbleToSeeTheAnswersForTheQuestionsOfTheChosenAQuiz() throws InterruptedException {
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
        click(nameField)
                .type(name)
                .click(signInButton)
                .click(quizTitle)
                .click(answer5)
                .click(submit);

        assertNodeExists(ViewMessages.WINNER);
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
