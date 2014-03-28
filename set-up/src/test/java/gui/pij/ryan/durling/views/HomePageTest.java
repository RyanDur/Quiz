package gui.pij.ryan.durling.views;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.views.pages.Home;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class HomePageTest extends GuiTest {

    private String addQuiz = "Add Quiz";
    private String quizName = "Name";
    private String addQuizField = "#create-quiz";
    private String createQuiz = "Create Quiz";
    private String button = ".button";
    private QuizCreator mockQuizCreator;
    private String addQuestionField = "#add-question";
    private String question = "pancakes";
    private String addQuestion = "#add-question-button";
    private String scoreField  = "#score";
    private String score = "9";
    private String addAnswerField = "#add-answer";
    private String answer = "of course";
    private String addAnswer = "#add-answer-button";
    private String incorrectRadio = "#incorrect";
    private String correctRadio = "#correct";

    @Override
    protected Parent getRootNode() {
        mockQuizCreator = mock(QuizCreator.class);
        when(mockQuizCreator.getName()).thenReturn(quizName);
        return new Home(mockQuizCreator);
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

        verifyThat("#header #title", hasText(quizName));
        verify(mockQuizCreator).createQuiz(quizName);
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
        String never = "never";
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
        String never = "never";
        String addAnotherQuestionButton = "#add-another-question";
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
}
