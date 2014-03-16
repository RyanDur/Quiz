package acceptance.steps.pij.ryan.durling.services;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.servers.QuizServer;
import pij.ryan.durling.services.QuizCreator;
import pij.ryan.durling.services.QuizCreatorImpl;
import pij.ryan.durling.registry.Question;
import pij.ryan.durling.registry.Quiz;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.addAll;
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
            quizId = quizCreator.create(name);
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

    @When("^a user adds a question like \"([^\"]*)\"$")
    public void a_user_adds_a_question_like(String question) throws Throwable {
        Set<Question> questions = getQuestions(mockQuestion);
        when(mockQuiz.getQuestions()).thenReturn(questions);
        when(mockQuestion.getQuestion()).thenReturn(question);

        try {
            quizCreator.addQuestion(mockQuestion);
        } catch (IllegalArgumentException e) {
            thrown = e;
        }
    }

    @And("^a user adds a question$")
    public void a_user_adds_a_question() throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @Then("^the question should be added$")
    public void the_question_should_be_added() throws Throwable {
        assertThat(thrown, is(not(instanceOf(IllegalArgumentException.class))));
        verify(mockQuizServer).createQuestion(anyString());
        verify(mockQuiz).addQuestion(eq(mockQuestion));
    }

    @Then("^the question should not be added$")
    public void the_question_should_not_be_added() throws Throwable {
        verify(mockQuiz, never()).addQuestion(eq(mockQuestion));
    }


    @Then("^the question should not be created$")
    public void the_question_should_not_be_created() throws Throwable {
        assertThat(thrown, is(instanceOf(IllegalArgumentException.class)));
        verify(mockQuizServer, never()).createQuestion(anyString());
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

    @And("^a user creates a question with \"([^\"]*)\"$")
    public void a_user_creates_a_question_with(String questionString) throws Throwable {
        if (nullValue.equals(questionString)) questionString = null;
        when(mockQuizServer.createQuestion(anyString())).thenReturn(mockQuestion);

        try {
            question = quizCreator.createQuestion(questionString);
        } catch (IllegalArgumentException e) {
            thrown = e;
        }
    }

    @And("^adds the question to the quiz$")
    public void adds_the_question_to_the_quiz() throws Throwable {
        quizCreator.addQuestion(question);
    }

    private Set<Question> getQuestions(Question... mockQuestions) {
        Set<Question> questions = new HashSet<>();
        addAll(questions, mockQuestions);
        return questions;
    }
}