package acceptance.steps.pij.ryan.durling.controllers;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.controllers.QuizCreatorImpl;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.resources.Server;
import pij.ryan.durling.resources.ServerLink;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class QuizCreatorSteps {

    private QuizCreator quizCreator;
    private Server mockServer = mock(Server.class);
    private Quiz mockQuiz = mock(Quiz.class);
    private Question mockQuestion = mock(Question.class);
    private Integer quizId;
    private Throwable thrown;

    @Given("^a user has a quiz creator$")
    public void a_user_has_a_quiz_creator() throws Throwable {
        ServerLink serverLink = mock(ServerLink.class);
        when(serverLink.getServer()).thenReturn(mockServer);
        quizCreator = new QuizCreatorImpl(serverLink);
    }

    @When("^a user creates a quiz named \"([^\"]*)\"$")
    public void a_user_creates_a_quiz_named(String name) throws Throwable {
        name = ifNull(name);

        when(mockQuiz.getName()).thenReturn(name);
        when(mockQuiz.getId()).thenReturn(5);
        when(mockServer.createQuiz(anyString())).thenReturn(mockQuiz);

        try {
            quizCreator.createQuiz(name);
            quizId = quizCreator.getQuizId();
        } catch (IllegalArgumentException e) {
            thrown = e;
        }
    }

    @Then("^they should have a quiz with the name \"([^\"]*)\"$")
    public void they_should_have_a_quiz_with_the_name(String expected) throws Throwable {
        String actual = quizCreator.getName();

        assertThat(actual, is(equalTo(expected)));
        verify(mockServer).createQuiz(anyString());
        verify(mockQuiz).getId();
    }

    @Then("^a quiz should not be created$")
    public void a_quiz_should_not_be_created() throws Throwable {
        verify(mockServer, never()).createQuiz(anyString());
    }

    @And("^not return the quiz ID$")
    public void not_return_the_quiz_ID() throws Throwable {
        assertThat(quizId, is(nullValue()));
        verify(mockQuiz, never()).getId();
    }

    @Then("^the question should be added$")
    public void the_question_should_be_added() throws Throwable {
        verify(mockServer).createQuestion(anyString(), anyInt());
    }

    @Then("^the question should not be created$")
    public void the_question_should_not_be_created() throws Throwable {
        verify(mockServer, never()).createQuestion(anyString(), anyInt());
    }

    @When("^a user saves the quiz$")
    public void a_user_saves_the_quiz() throws Throwable {
        try {
            quizCreator.save();
        } catch (IllegalQuizCreationException | InvalidQuizException e) {
            thrown = e;
        }
    }

    @Then("^the quiz should not be saved$")
    public void the_quiz_should_not_be_saved() throws Throwable {
        verify(mockServer, never()).save(eq(mockQuiz));
    }

    @And("^return the quiz ID$")
    public void return_the_quiz_ID() throws Throwable {
        assertNotNull(quizId);
    }

    @Then("^the quiz should be saved$")
    public void the_quiz_should_be_saved() throws Throwable {
        verify(mockServer).save(eq(mockQuiz));
    }

    @When("^a user creates a question with \"([^\"]*)\" and (.*)$")
    public void a_user_creates_a_question_with_and(String questionString, int value) throws Throwable {
        questionString = ifNull(questionString);

        when(mockQuestion.getQuestion()).thenReturn(questionString);
        when(mockQuestion.getValue()).thenReturn(value);
        when(mockServer.createQuestion(anyString(), anyInt())).thenReturn(mockQuestion);

        try {
            quizCreator.addQuestion(questionString, value);
        } catch (IllegalArgumentException | IllegalQuizCreationException e) {
            thrown = e;
        }
    }

    @Then("^the answer should be added$")
    public void the_answer_should_be_added() throws Throwable {
        verify(mockServer).createAnswer(anyString(), anyBoolean());
    }

    @And("^a user creates an \"([^\"]*)\" that is \"([^\"]*)\"$")
    public void a_user_creates_an_that_is(String answerString, String valueString) throws Throwable {
        boolean value = ifTrue(valueString);
        answerString = ifNull(answerString);

        try {
            quizCreator.addAnswer(answerString, value);
        } catch (IllegalArgumentException | IllegalQuizCreationException e) {
            thrown = e;
        }
    }

    @And("^the quiz is \"([^\"]*)\"$")
    public void the_quiz_is(String validString) throws Throwable {
        boolean valid = ifTrue(validString);
        when(mockQuiz.valid()).thenReturn(valid);
    }

    @And("^throw an IllegalArgumentException$")
    public void throw_an_IllegalArgumentException() throws Throwable {
        assertThat(thrown, is(instanceOf(IllegalArgumentException.class)));
    }

    @And("^throw an IllegalQuizCreationException$")
    public void throw_an_IllegalQuizCreationException() throws Throwable {
        assertThat(thrown, is(instanceOf(IllegalQuizCreationException.class)));
    }

    @And("^throw an InvalidQuizException$")
    public void throw_an_InvalidQuizException() throws Throwable {
        assertThat(thrown, is(instanceOf(InvalidQuizException.class)));
    }

    @Then("^the answer should not be added$")
    public void the_answer_should_not_be_added() throws Throwable {
        verify(mockQuestion, never()).createAnswer(anyString(), anyBoolean());
    }

    @And("^have the message \"([^\"]*)\"$")
    public void have_the_message(String message) throws Throwable {
        assertThat(thrown.getMessage(), is(equalTo(message)));
    }

    @And("^the question is \"([^\"]*)\"$")
    public void the_question_is(String validString) throws Throwable {
        boolean value = ifTrue(validString);
        when(mockQuestion.valid()).thenReturn(value);
    }

    @Then("^the question should not be added$")
    public void the_question_should_not_be_added() throws Throwable {
        verify(mockQuiz, never()).add(eq(mockQuestion));
    }

    @Then("^the answer should not be created$")
    public void the_answer_should_not_be_created() throws Throwable {
        verify(mockServer, never()).createAnswer(anyString(), anyBoolean());
    }

    @Then("^a user should be able to get the \"([^\"]*)\"$")
    public void a_user_should_be_able_to_get_the(String question) throws Throwable {
        when(mockQuestion.getQuestion()).thenReturn(question);
        assertThat(quizCreator.getQuestion(), is(equalTo(question)));
    }

    @Then("^a user should be able to lock a quiz$")
    public void a_user_should_be_able_to_lock_a_quiz() throws Throwable {
        quizCreator.lockQuiz(quizCreator.getQuizId());
        verify(mockServer).lock(anyInt());
    }

    @Then("^a user should be able to unlock a quiz$")
    public void a_user_should_be_able_to_unlock_a_quiz() throws Throwable {
        quizCreator.unlockQuiz(quizCreator.getQuizId());
        verify(mockServer).unlock(anyInt());
    }

    private String ifNull(String argument) {
        String nullValue = "null";
        if (nullValue.equals(argument)) argument = null;
        return argument;
    }

    private boolean ifTrue(String argument) {
        boolean value = false;
        String aTrueValue = "true";
        if (aTrueValue.equals(argument)) value = true;
        return value;
    }
}