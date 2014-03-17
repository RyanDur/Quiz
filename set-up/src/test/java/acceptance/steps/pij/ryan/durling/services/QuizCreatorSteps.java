package acceptance.steps.pij.ryan.durling.services;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.registry.Answer;
import pij.ryan.durling.registry.Question;
import pij.ryan.durling.registry.Quiz;
import pij.ryan.durling.resources.QuizServer;
import pij.ryan.durling.services.QuizCreator;
import pij.ryan.durling.services.QuizCreatorImpl;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class QuizCreatorSteps {

    private QuizCreator quizCreator;
    private QuizServer mockQuizServer = mock(QuizServer.class);
    private Quiz mockQuiz = mock(Quiz.class);
    private Question mockQuestion = mock(Question.class);
    private Integer quizId;
    private Throwable thrown;
    private Question question;
    private String nullValue = "null";

    @Given("^a user has a quiz creator$")
    public void a_user_has_a_quiz_creator() throws Throwable {
        quizCreator = new QuizCreatorImpl(mockQuizServer);
    }

    @When("^a user creates a quiz named \"([^\"]*)\"$")
    public void a_user_creates_a_quiz_named(String name) throws Throwable {
        if (nullValue.equals(name)) name = null;
        int id = 3;

        when(mockQuiz.getName()).thenReturn(name);
        when(mockQuiz.getId()).thenReturn(id);
        when(mockQuizServer.createQuiz(anyString())).thenReturn(mockQuiz);

        try {
            quizId = quizCreator.createQuiz(name);
        } catch (IllegalArgumentException e) {
            thrown = e;
        }
    }

    @Then("^they should have a quiz with the name \"([^\"]*)\"$")
    public void they_should_have_a_quiz_with_the_name(String expected) throws Throwable {
        Quiz actual = quizCreator.getQuiz();

        assertThat(actual.getName(), is(equalTo(expected)));
        assertThat(thrown, is(nullValue()));
        verify(mockQuizServer).createQuiz(anyString());
        verify(mockQuiz).getId();
    }

    @Then("^a quiz should not be created$")
    public void a_quiz_should_not_be_created() throws Throwable {
        assertThat(thrown, is(instanceOf(IllegalArgumentException.class)));
        verify(mockQuizServer, never()).createQuiz(anyString());
    }

    @And("^not return the quiz ID$")
    public void not_return_the_quiz_ID() throws Throwable {
        assertThat(quizId, is(nullValue()));
        verify(mockQuiz, never()).getId();
    }

    @Then("^the question should be added$")
    public void the_question_should_be_added() throws Throwable {
        assertThat(thrown, is(not(instanceOf(IllegalArgumentException.class))));
        verify(mockQuiz).createQuestion(anyString(), anyInt());
    }

    @Then("^the question should not be created$")
    public void the_question_should_not_be_created() throws Throwable {
        assertThat(thrown, is(instanceOf(IllegalArgumentException.class)));
        verify(mockQuiz, never()).createQuestion(anyString(), anyInt());
    }

    @When("^a user saves the quiz$")
    public void a_user_saves_the_quiz() throws Throwable {
        quizCreator.save();
    }

    @Then("^the quiz should not be saved$")
    public void the_quiz_should_not_be_saved() throws Throwable {
        verify(mockQuizServer, never()).save(eq(mockQuiz));
    }

    @And("^return the quiz ID$")
    public void return_the_quiz_ID() throws Throwable {
        assertNotNull(quizId);
    }

    @Then("^the quiz should be saved$")
    public void the_quiz_should_be_saved() throws Throwable {
        verify(mockQuizServer).save(eq(mockQuiz));
    }

    @When("^a user creates a question with \"([^\"]*)\" and (\\d+)$")
    public void a_user_creates_a_question_with_and(String questionString, int value) throws Throwable {
        if (nullValue.equals(questionString)) questionString = null;

        when(mockQuestion.getQuestion()).thenReturn(questionString);
        when(mockQuestion.getValue()).thenReturn(value);
        when(mockQuiz.createQuestion(anyString(), anyInt())).thenReturn(mockQuestion);

        try {
            question = quizCreator.createQuestion(questionString, value);
        } catch (IllegalArgumentException | IllegalQuizCreationException e) {
            thrown = e;
        }
    }

    @Then("^the answer should be added$")
    public void the_answer_should_be_added() throws Throwable {
        assertThat(thrown, is(not(instanceOf(IllegalArgumentException.class))));
        verify(mockQuestion).createAnswer(anyString(), anyBoolean());
    }

    @When("^a user adds \"([^\"]*)\" and mark if its \"([^\"]*)\"$")
    public void a_user_adds_and_mark_if_its(String answerString, String valueString) throws Throwable {
        boolean value = false;
        if (valueString.equals("true")) value = true;
        if (nullValue.equals(answerString)) answerString = null;
        Answer mockAnswer = mock(Answer.class);

        when(mockAnswer.getAnswer()).thenReturn(answerString);
        when(mockAnswer.getValue()).thenReturn(value);
        when(mockQuestion.createAnswer(anyString(), anyBoolean())).thenReturn(mockAnswer);

        try {
            quizCreator.createAnswer(question, answerString, value);
        } catch (IllegalArgumentException e) {
            thrown = e;
        }
    }


    @Then("^the answer should not should be added$")
    public void the_answer_should_not_should_be_added() throws Throwable {
        assertThat(thrown, is(instanceOf(IllegalArgumentException.class)));
        verify(mockQuestion, never()).createAnswer(anyString(), anyBoolean());
    }

    @And("^is a valid quiz$")
    public void is_a_valid_quiz() throws Throwable {
        when(mockQuiz.isValid()).thenReturn(true);
    }

    @And("^is an invalid quiz$")
    public void is_an_invalid_quiz() throws Throwable {
        when(mockQuiz.isValid()).thenReturn(false);
    }

    @Then("^the question should not be created without a quiz$")
    public void the_question_should_not_be_created_without_a_quiz() throws Throwable {
        assertThat(thrown, is(instanceOf(IllegalQuizCreationException.class)));
        verify(mockQuiz, never()).createQuestion(anyString(), anyInt());
    }
}