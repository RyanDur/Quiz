package acceptance.steps.pij.ryan.durling.controllers;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.controllers.QuizCreatorImpl;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;
import pij.ryan.durling.resources.QuizMaker;
import pij.ryan.durling.resources.Server;
import pij.ryan.durling.resources.ServerLink;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class QuizCreatorSteps {

    private QuizCreator quizCreator;
    private Integer quizId;
    private Throwable thrown;
    private QuizMaker quizMaker;

    @Given("^a user has a quiz creator$")
    public void a_user_has_a_quiz_creator() throws Throwable {
        Server mockServer = mock(Server.class);
        ServerLink serverLink = mock(ServerLink.class);
        when(serverLink.getServer()).thenReturn(mockServer);
        quizMaker = mock(QuizMaker.class);
        when(mockServer.getQuizMaker()).thenReturn(quizMaker);
        quizCreator = new QuizCreatorImpl(serverLink);
    }

    @When("^a user creates a quiz named \"([^\"]*)\"$")
    public void a_user_creates_a_quiz_named(String name) throws Throwable {
        name = ifNull(name);

        when(quizMaker.getName()).thenReturn(name);
        when(quizMaker.getId()).thenReturn(5);

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
        verify(quizMaker).createQuiz(anyString());
    }

    @Then("^a quiz should not be created$")
    public void a_quiz_should_not_be_created() throws Throwable {
        verify(quizMaker, never()).createQuiz(anyString());
    }

    @And("^not return the quiz ID$")
    public void not_return_the_quiz_ID() throws Throwable {
        assertThat(quizId, is(nullValue()));
        verify(quizMaker, never()).getId();
    }

    @Then("^the question should be added$")
    public void the_question_should_be_added() throws Throwable {
        verify(quizMaker).addQuestion(anyString(), anyInt());
    }

    @Then("^the question should not be created$")
    public void the_question_should_not_be_created() throws Throwable {
        verify(quizMaker, never()).addQuestion(anyString(), anyInt());
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
        verify(quizMaker, never()).save();
    }

    @And("^return the quiz ID$")
    public void return_the_quiz_ID() throws Throwable {
        assertNotNull(quizId);
    }

    @Then("^the quiz should be saved$")
    public void the_quiz_should_be_saved() throws Throwable {
        verify(quizMaker).save();
    }

    @When("^a user creates a question with \"([^\"]*)\" and (.*)$")
    public void a_user_creates_a_question_with_and(String questionString, int value) throws Throwable {
        questionString = ifNull(questionString);

        when(quizMaker.getQuestion()).thenReturn(questionString);
        when(quizMaker.getQuestionValue()).thenReturn(value);

        try {
            quizCreator.addQuestion(questionString, value);
        } catch (IllegalArgumentException | IllegalQuizCreationException e) {
            thrown = e;
        }
    }

    @Then("^the answer should be added$")
    public void the_answer_should_be_added() throws Throwable {
//        verify(mockServer).createAnswer(anyString(), anyBoolean());
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
        when(quizMaker.validQuiz()).thenReturn(valid);
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
        verify(quizMaker, never()).addAnswer(anyString(), anyBoolean());
    }

    @And("^have the message \"([^\"]*)\"$")
    public void have_the_message(String message) throws Throwable {
        assertThat(thrown.getMessage(), is(equalTo(message)));
    }

    @And("^the question is \"([^\"]*)\"$")
    public void the_question_is(String validString) throws Throwable {
        boolean value = ifTrue(validString);
        when(quizMaker.validQuestion()).thenReturn(value);
    }

    @Then("^the question should not be added$")
    public void the_question_should_not_be_added() throws Throwable {
        verify(quizMaker, never()).addQuestion(anyString(), anyInt());
    }

    @Then("^the answer should not be created$")
    public void the_answer_should_not_be_created() throws Throwable {
        verify(quizMaker, never()).addAnswer(anyString(), anyBoolean());
    }

    @Then("^a user should be able to get the \"([^\"]*)\"$")
    public void a_user_should_be_able_to_get_the(String question) throws Throwable {
        when(quizMaker.getQuestion()).thenReturn(question);
        assertThat(quizCreator.getQuestion(), is(equalTo(question)));
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

    @Given("^a quiz is never created$")
    public void a_quiz_is_never_created() throws Throwable {
        when(quizMaker.empty()).thenReturn(true);
    }
}