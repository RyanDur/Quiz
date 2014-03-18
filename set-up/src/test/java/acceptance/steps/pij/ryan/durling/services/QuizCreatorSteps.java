package acceptance.steps.pij.ryan.durling.services;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;
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

    @Given("^a user has a quiz creator$")
    public void a_user_has_a_quiz_creator() throws Throwable {
        quizCreator = new QuizCreatorImpl(mockQuizServer);
    }

    @When("^a user creates a quiz named \"([^\"]*)\"$")
    public void a_user_creates_a_quiz_named(String name) throws Throwable {
        name = ifNull(name);

        when(mockQuiz.getName()).thenReturn(name);
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
        verify(mockQuizServer).createQuiz(anyString());
        verify(mockQuiz).getId();
    }

    @Then("^a quiz should not be created$")
    public void a_quiz_should_not_be_created() throws Throwable {
        verify(mockQuizServer, never()).createQuiz(anyString());
    }

    @And("^not return the quiz ID$")
    public void not_return_the_quiz_ID() throws Throwable {
        assertThat(quizId, is(nullValue()));
        verify(mockQuiz, never()).getId();
    }

    @Then("^the question should be added$")
    public void the_question_should_be_added() throws Throwable {
        verify(mockQuiz).createQuestion(anyString(), anyInt());
    }

    @Then("^the question should not be created$")
    public void the_question_should_not_be_created() throws Throwable {
        verify(mockQuiz, never()).createQuestion(anyString(), anyInt());
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
        questionString = ifNull(questionString);

        when(mockQuestion.getQuestion()).thenReturn(questionString);
        when(mockQuestion.getValue()).thenReturn(value);
        when(mockQuiz.createQuestion(anyString(), anyInt())).thenReturn(mockQuestion);

        try {
            question = quizCreator.createQuestion(questionString, value);
            when(mockQuiz.contains(eq(mockQuestion))).thenReturn(true);
        } catch (IllegalArgumentException | IllegalQuizCreationException e) {
            thrown = e;
        }
    }

    @Then("^the answer should be added$")
    public void the_answer_should_be_added() throws Throwable {
        verify(mockQuestion).createAnswer(anyString(), anyBoolean());
    }

    @When("^a user adds \"([^\"]*)\" and mark if its \"([^\"]*)\"$")
    public void a_user_adds_and_mark_if_its(String answerString, String valueString) throws Throwable {
        boolean value = ifTrue(valueString);
        String answer = ifNull(answerString);

        try {
            quizCreator.createAnswer(question, answer, value);
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

    @And("^a user adds a question that is not apart of the quiz$")
    public void a_user_adds_a_question_that_is_not_apart_of_the_quiz() throws Throwable {
        question = mock(Question.class);
        when(mockQuiz.contains(eq(question))).thenReturn(false);
    }

    @Then("^the answer should not be added$")
    public void the_answer_should_not_be_added() throws Throwable {
        verify(mockQuestion, never()).createAnswer(anyString(), anyBoolean());
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