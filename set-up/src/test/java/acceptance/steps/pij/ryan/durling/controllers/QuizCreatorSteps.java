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
import pij.ryan.durling.resources.ServerLink;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.*;

public class QuizCreatorSteps {

    private QuizCreator quizCreator;
    private Throwable thrown;
    private QuizMaker quizMaker;

    @Given("^a user has a quiz creator$")
    public void a_user_has_a_quiz_creator() throws Throwable {
        ServerLink serverLink = mock(ServerLink.class);
        quizMaker = mock(QuizMaker.class);
        when(serverLink.getQuizMaker()).thenReturn(quizMaker);
        quizCreator = new QuizCreatorImpl(serverLink);
    }

    @When("^a user creates a quiz named \"([^\"]*)\"$")
    public void a_user_creates_a_quiz_named(String name) throws Throwable {
        name = ifNull(name);

        try {
            quizCreator.createQuiz(name);
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

    @Then("^the quiz should be saved$")
    public void the_quiz_should_be_saved() throws Throwable {
        verify(quizMaker).save();
    }

    @When("^a user creates a question with \"([^\"]*)\" and (.*)$")
    public void a_user_creates_a_question_with_and(String questionString, int value) throws Throwable {
        questionString = ifNull(questionString);

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

    @When("^a user asks for the available quizzes$")
    public void a_user_asks_for_the_available_quizzes() throws Throwable {
        quizCreator.getAvailableQuizzes();
    }

    @Then("^a user should receive the available quizzes$")
    public void a_user_should_receive_the_available_quizzes() throws Throwable {
        verify(quizMaker).getAvailableQuizzes();
    }

    @When("^a user asks for the closed quizzes$")
    public void a_user_asks_for_the_closed_quizzes() throws Throwable {
        quizCreator.getClosedQuizzes();
    }

    @Then("^a user should receive the closed quizzes$")
    public void a_user_should_receive_the_closed_quizzes() throws Throwable {
        verify(quizMaker).getClosedQuizzes();
    }

    @When("^a user closes quiz (\\d+)$")
    public void a_user_closes_quiz(int id) throws Throwable {
        quizCreator.closeQuiz(id);
    }

    @Then("^quiz (\\d+) should be closed$")
    public void quiz_should_be_closed(int id) throws Throwable {
        verify(quizMaker).closeQuiz(id);
    }

    @When("^a user opens quiz (\\d+)$")
    public void a_user_opens_quiz(int id) throws Throwable {
        quizCreator.openQuiz(id);
    }

    @Then("^quiz (\\d+) should be opened$")
    public void quiz_should_be_opened(int id) throws Throwable {
        verify(quizMaker).openQuiz(id);
    }
}