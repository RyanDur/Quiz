package gui.pij.ryan.durling.views;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.views.pages.Home;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class HomePageTest extends GuiTest {

    private String addQuiz = "Add Quiz";
    private String quizName = "Name";
    private String addQuizField = "#create-quiz";
    private String createQuiz = "Create Quiz";
    private String button = ".button";
    private QuizCreator mockQuizCreator;
    private Quiz mockQuiz = mock(Quiz.class);
    private String addQuestionField = "#add-question";
    private String question = "pancakes";
    private String addQuestion = "#add-question-button";
    private String scoreField  = "#score";
    private String score = "9";
    private Question mockQuestion;
    private String addAnswerField = "#add-answer";
    private String answer = "of course";
    private Answer mockAnswer;
    private String addAnswer = "#add-answer-button";

    @Override
    protected Parent getRootNode() {
        mockQuizCreator = mock(QuizCreator.class);
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
        setup();
        click(addQuiz)
                .click(addQuizField)
                .type(quizName)
                .click(createQuiz);

        verifyThat("#header #title", hasText(quizName));
        verify(mockQuizCreator).createQuiz(quizName);
    }

    @Test
    public void shouldBeAbleToAddAQuestionAfterCreatingAQuiz() {
        setup();
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
        setup();
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
        setup();
        click(addQuiz)
                .click(addQuizField)
                .type(quizName)
                .click(createQuiz)
                .click(addQuestionField)
                .type(question)
                .click(scoreField)
                .type(score)
                .click(addQuestion);

        verify(mockQuizCreator).addQuestion(eq(mockQuestion));
    }

    @Test
    public void shouldBeAbleToWriteAnswersAfterCreatingAQuestion() {
        setup();

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
        setup();

        String correctRadio = "#correct";
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

        verify(mockQuizCreator).createAnswer(answer, true);
        verify(mockQuizCreator).addAnswer(mockQuestion, mockAnswer);
    }

    @Test
    public void shouldBeaAbleAddAnIncorrectAnswerToAQuestion() throws IllegalQuizCreationException {
        setup();

        String incorrectRadio = "#incorrect";
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

        verify(mockQuizCreator).createAnswer(answer, false);
        verify(mockQuizCreator).addAnswer(mockQuestion, mockAnswer);
    }

    private void setup() {
        when(mockQuizCreator.getName()).thenReturn(quizName);
        when(mockQuiz.getName()).thenReturn(quizName);
        mockQuestion = mock(Question.class);
        mockAnswer = mock(Answer.class);
        try {
            when(mockQuizCreator.createQuestion(anyString(), anyInt())).thenReturn(mockQuestion);
            when(mockQuizCreator.createAnswer(anyString(), anyBoolean())).thenReturn(mockAnswer);
        } catch (IllegalQuizCreationException e) {
            e.printStackTrace();
        }
    }
}
