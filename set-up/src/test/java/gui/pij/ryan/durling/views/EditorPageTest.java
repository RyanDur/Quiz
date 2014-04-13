package gui.pij.ryan.durling.views;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.views.pages.EditorImpl;
import pij.ryan.durling.views.pages.Views;
import pij.ryan.durling.views.pages.ViewsImpl;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class EditorPageTest extends GuiTest {

    private String addQuiz = ViewMessages.ADD_QUIZ_BUTTON;
    private String quizName = "Name";
    private String addQuizField = "#" + ViewMessages.CREATE_QUIZ_TITLE_FIELD_ID;
    private String createQuiz = ViewMessages.CREATE_QUIZ_BUTTON;
    private String button = ".button";
    private QuizCreator mockQuizCreator;
    private String addQuestionField = "#" + ViewMessages.QUESTION_INPUT_AREA_ID;
    private String question = "pancakes";
    private String addQuestion = "#" + ViewMessages.ADD_QUESTION_BUTTON_ID;
    private String scoreField  = "#" + ViewMessages.SCORE_ID;
    private String score = "9";
    private String addAnswerField = "#" + ViewMessages.ANSWER_AREA_ID;
    private String answer = "of course";
    private String addAnswer = "#" + ViewMessages.ANSWER_BUTTON_ID;
    private String incorrectRadio = "#" + ViewMessages.INCORRECT_ID;
    private String correctRadio = "#" + ViewMessages.CORRECT_ID;
    private String never = "never";
    private String addAnotherQuestionButton = "#" + ViewMessages.ANOTHER_QUESTION_BUTTON_ID;
    private String foobar = "Bacon";

    @Override
    protected Parent getRootNode() {
        Views views = new ViewsImpl();
        mockQuizCreator = mock(QuizCreator.class);
        when(mockQuizCreator.getQuestion()).thenReturn(question, foobar);
        when(mockQuizCreator.getName()).thenReturn(quizName);

        return new EditorImpl(mockQuizCreator, views);
    }

    @Test
    public void shouldHaveAButton() {
        verifyThat(button, hasText(addQuiz));
    }

    @Test
    public void shouldAskToCreateAQuizAfterClickingButton() {
        click(addQuiz);
        verifyThat(button, hasText(createQuiz));
    }

    @Test
    public  void shouldBeAbleToAddANameOfAQuiz() {
        click(addQuiz).click(addQuizField).type(quizName);
        verifyThat(addQuizField, hasText(quizName));
    }

    @Test
    public void shouldBeAbleToSeeTheQuizNameAfterCreatingAQuiz() {
        click(addQuiz)
                .click(addQuizField)
                .type(quizName)
                .click(createQuiz);

        verifyThat("#" + ViewMessages.TITLE_ID, hasText(quizName));
        verify(mockQuizCreator).createQuiz(quizName);
    }

    @Test
    public void shouldBeAbleToLockAQuizYouAreWorkingOn() {
        click(addQuiz)
                .click(addQuizField)
                .type(quizName)
                .click(createQuiz)
                .click("#" + ViewMessages.LOCK_QUIZ_BUTTON_ID);

        verify(mockQuizCreator).lockQuiz(anyInt());
        verifyThat("#" + ViewMessages.LOCK_QUIZ_BUTTON_ID, hasText(ViewMessages.LOCKED_QUIZ_BUTTON));
    }

    @Test
    public void shouldBeAbleToAddAQuestionAfterCreatingAQuiz() {
        click(addQuiz)
                .click(addQuizField)
                .type(quizName)
                .click(createQuiz)
                .click(addQuestionField)
                .type(question);

        verifyThat(addQuestionField, hasText(question));
    }

    @Test
    public void shouldBeAbleToAddAScoreToAQuestion() {
        click(addQuiz)
                .click(addQuizField)
                .type(quizName)
                .click(createQuiz)
                .click(addQuestionField)
                .type(question)
                .click(scoreField)
                .type(score);

        verifyThat(scoreField, hasText(score));
    }

    @Test
    public void shouldBeAbleToAddAQuestionToAQuiz() throws IllegalQuizCreationException {
        click(addQuiz)
                .click(addQuizField)
                .type(quizName)
                .click(createQuiz)
                .click(addQuestionField)
                .type(question)
                .click(scoreField)
                .type(score)
                .click(addQuestion);

        verify(mockQuizCreator).addQuestion(question, Integer.parseInt(score));
    }

    @Test
    public void shouldBeAbleToWriteAnswersAfterCreatingAQuestion() {
        click(addQuiz)
                .click(addQuizField)
                .type(quizName)
                .click(createQuiz)
                .click(addQuestionField)
                .type(question)
                .click(scoreField)
                .type(score)
                .click(addQuestion)
                .click(addAnswerField)
                .type(answer);

        verifyThat(addAnswerField, hasText(answer));
    }

    @Test
    public void shouldBeaAbleAddACorrectAnswerToAQuestion() throws IllegalQuizCreationException {
        click(addQuiz)
                .click(addQuizField)
                .type(quizName)
                .click(createQuiz)
                .click(addQuestionField)
                .type(question)
                .click(scoreField)
                .type(score)
                .click(addQuestion)
                .click(addAnswerField)
                .type(answer)
                .click(correctRadio)
                .click(addAnswer);

        verify(mockQuizCreator).addAnswer(answer, true);
    }

    @Test
    public void shouldBeaAbleAddAnIncorrectAnswerToAQuestion() throws IllegalQuizCreationException {
        click(addQuiz)
                .click(addQuizField)
                .type(quizName)
                .click(createQuiz)
                .click(addQuestionField)
                .type(question)
                .click(scoreField)
                .type(score)
                .click(addQuestion)
                .click(addAnswerField)
                .type(answer)
                .click(incorrectRadio)
                .click(addAnswer);

        verify(mockQuizCreator).addAnswer(answer, false);
    }

    @Test
    public void shouldBeAbleToAddMultipleAnswersToAQuestion() throws IllegalQuizCreationException {
        click(addQuiz)
                .click(addQuizField)
                .type(quizName)
                .click(createQuiz)
                .click(addQuestionField)
                .type(question)
                .click(scoreField)
                .type(score)
                .click(addQuestion)
                .click(addAnswerField)
                .type(answer)
                .click(correctRadio)
                .click(addAnswer)
                .click(addAnswerField)
                .type(never)
                .click(incorrectRadio)
                .click(addAnswer);

        verify(mockQuizCreator, times(2)).addAnswer(anyString(), anyBoolean());
        verifyThat(addAnswerField, hasText(""));
    }

    @Test
    public void shouldBeAbleToAddMultipleQuestionsToAQuiz() throws IllegalQuizCreationException {
        click(addQuiz)
                .click(addQuizField)
                .type(quizName)
                .click(createQuiz)
                .click(addQuestionField)
                .type(question)
                .click(scoreField)
                .type(score)
                .click(addQuestion)
                .click(addAnswerField)
                .type(answer)
                .click(correctRadio)
                .click(addAnswer)
                .click(addAnswerField)
                .type(never)
                .click(incorrectRadio)
                .click(addAnswer)
                .click(addAnotherQuestionButton);

        verify(mockQuizCreator, times(2)).addAnswer(anyString(), anyBoolean());
        verifyThat(addQuestionField, hasText(""));
    }

    @Test
    public void shouldBeAbleToSaveAQuiz() throws InvalidQuizException, IllegalQuizCreationException {
        when(mockQuizCreator.validQuiz()).thenReturn(true);
        click(addQuiz)
                .click(addQuizField)
                .type(quizName)
                .click(createQuiz)
                .click(addQuestionField)
                .type(question)
                .click(scoreField)
                .type(score)
                .click(addQuestion)
                .click(addAnswerField)
                .type(answer)
                .click(correctRadio)
                .click(addAnswer)
                .click(addAnswerField)
                .type(never)
                .click(incorrectRadio)
                .click(addAnswer)
                .click(addAnotherQuestionButton)
                .click(addQuestionField)
                .type(foobar)
                .click(scoreField)
                .type(score)
                .click(addQuestion)
                .click(addAnswerField)
                .type(answer)
                .click(correctRadio)
                .click(addAnswer)
                .click(addAnswerField)
                .type(never)
                .click(incorrectRadio)
                .click(addAnswer)
                .click("#" + ViewMessages.SAVE_BUTTON_ID);

        verify(mockQuizCreator).save();
    }
}
