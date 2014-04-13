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

import static org.loadui.testfx.Assertions.assertNodeExists;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class EditorPageTest extends GuiTest {

    private String addQuiz = ViewMessages.ADD_QUIZ_BUTTON;
    private String addQuizField = "#" + ViewMessages.CREATE_QUIZ_TITLE_FIELD_ID;
    private String createQuiz = ViewMessages.CREATE_QUIZ_BUTTON;
    private String addQuestionField = "#" + ViewMessages.QUESTION_INPUT_AREA_ID;
    private String addQuestion = "#" + ViewMessages.ADD_QUESTION_BUTTON_ID;
    private String scoreField  = "#" + ViewMessages.SCORE_ID;
    private String addAnswerField = "#" + ViewMessages.ANSWER_AREA_ID;
    private String addAnswer = "#" + ViewMessages.ANSWER_BUTTON_ID;
    private String incorrectRadio = "#" + ViewMessages.INCORRECT_ID;
    private String correctRadio = "#" + ViewMessages.CORRECT_ID;
    private String addAnotherQuestionButton = "#" + ViewMessages.ANOTHER_QUESTION_BUTTON_ID;
    private String quizName = "Name";
    private String button = ".button";
    private String question = "pancakes";
    private String score = "9";
    private String answer = "of course";
    private String never = "never";
    private String foobar = "Bacon";
    private QuizCreator mockQuizCreator;
    private String lock = "#" + ViewMessages.LOCK_QUIZ_BUTTON_ID;

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
        String titleId = "#" + ViewMessages.TITLE_ID;

        click(addQuiz)
                .click(addQuizField)
                .type(quizName)
                .click(createQuiz);

        verifyThat(titleId, hasText(quizName));
        verify(mockQuizCreator).createQuiz(quizName);
    }

    @Test
    public void shouldBeAbleToLockAQuizYouAreWorkingOn() {
        String lockedQuiz = ViewMessages.LOCKED_QUIZ_BUTTON;

        click(addQuiz)
                .click(addQuizField)
                .type(quizName)
                .click(createQuiz)
                .click(lock);

        verify(mockQuizCreator).lockQuiz(anyInt());
        verifyThat(lock, hasText(lockedQuiz));
    }

    @Test
    public void shouldBeAbleToUnlockAQuizLocking() {
        String lockQuiz = ViewMessages.LOCK_QUIZ_BUTTON;

        click(addQuiz)
                .click(addQuizField)
                .type(quizName)
                .click(createQuiz)
                .click(lock).click(addQuestionField)
                .click(lock);

        verify(mockQuizCreator).unlockQuiz(anyInt());
        verifyThat(lock, hasText(lockQuiz));
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
        String save = "#" + ViewMessages.SAVE_BUTTON_ID;

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
                .click(save);

        verify(mockQuizCreator).save();
    }

    @Test
    public void shouldBeAbleToUnlockCurrentQuizAndAddANewQuizAfterSaving() throws InvalidQuizException, IllegalQuizCreationException {
        String addQuizId = "#" + ViewMessages.ADD_QUIZ_BUTTON_ID;
        String footer = "#" + ViewMessages.FOOTER_VIEW_ID;
        when(mockQuizCreator.validQuiz()).thenReturn(true);
        String save = "#" + ViewMessages.SAVE_BUTTON_ID;
        String header = "#" + ViewMessages.HEADER_VIEW_ID;

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
                .click(save);

        verify(mockQuizCreator).save();
        verify(mockQuizCreator).unlockQuiz(anyInt());
        assertNodeExists(header);
        assertNodeExists(addQuizId);
        assertNodeExists(footer);
    }
}
