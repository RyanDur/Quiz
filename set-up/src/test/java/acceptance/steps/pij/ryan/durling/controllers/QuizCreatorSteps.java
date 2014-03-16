package acceptance.steps.pij.ryan.durling.controllers;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.client.QuizClient;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.controllers.QuizCreatorImpl;
import pij.ryan.durling.registry.Question;
import pij.ryan.durling.registry.Quiz;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class QuizCreatorSteps {

    private QuizCreator quizCreator;
    private int quizId;
    private QuizClient mockQuizClient;
    private Quiz mockQuiz;

    @Given("^I have a quiz creator$")
    public void I_have_a_quiz_creator() throws Throwable {
        mockQuizClient = mock(QuizClient.class);
        quizCreator = new QuizCreatorImpl(mockQuizClient);
    }

    @When("^I create a quiz named \"([^\"]*)\"$")
    public void I_create_a_quiz_named(String name) throws Throwable {
        int id = 3;
        mockQuiz = mock(Quiz.class);
        when(mockQuiz.getName()).thenReturn(name);
        when(mockQuiz.getId()).thenReturn(id);
        when(mockQuizClient.createQuiz(anyString())).thenReturn(mockQuiz);

        quizId = quizCreator.create(name);
        verify(mockQuizClient).createQuiz(anyString());
    }

    @Then("^I should have a quiz with the name \"([^\"]*)\"$")
    public void I_should_have_a_quiz_with_the_name(String expected) throws Throwable {
        Quiz actual = quizCreator.get(quizId);

        assertThat(actual.getName(), is(equalTo(expected)));
    }

    @When("^I add a question like \"([^\"]*)\"$")
    public void I_add_a_question_like(String question) throws Throwable {
        Question mockQuestion = mock(Question.class);
        List<Question> questions = Arrays.asList(mockQuestion);
        when(mockQuizClient.createQuestion(anyString())).thenReturn(mockQuestion);
        when(mockQuiz.getQuestions()).thenReturn(questions);
        when(mockQuestion.getQuestion()).thenReturn(question);

        quizCreator.addQuestion(question, quizId);
        verify(mockQuizClient).createQuestion(anyString());
    }

    @Then("^the quiz should have that \"([^\"]*)\"$")
    public void the_quiz_should_have_that(String expected) throws Throwable {
        Quiz quiz = quizCreator.get(quizId);
        Question actual = quiz.getQuestions().get(0);

        assertThat(actual.getQuestion(), is(equalTo(expected)));
    }

    @Given("^I create a quiz without a \"([^\"]*)\"$")
    public void I_create_a_quiz_without_a(String name) throws Throwable {
        boolean expected = false;
        try {
            quizCreator.create(name);
        } catch (IllegalArgumentException e) {
            expected = true;
        }
        assertTrue(expected);
    }

    @Given("^I create a quiz named null$")
    public void I_create_a_quiz_named_null() throws Throwable {
        boolean expected = false;
        try {
            quizCreator.create(null);
        } catch (IllegalArgumentException e) {
            expected = true;
        }
        assertTrue(expected);
    }

    @Given("^I add an empty question like \"([^\"]*)\"$")
    public void I_add_an_empty_question_like(String question) throws Throwable {
        boolean expected = false;
        try {
            quizCreator.addQuestion(question, quizId);
        } catch (IllegalArgumentException e) {
            expected = true;
        }
        assertTrue(expected);
    }

    @Given("^I try to add a null question$")
    public void I_try_to_add_a_null_question() throws Throwable {
        boolean expected = false;
        try {
            quizCreator.addQuestion(null, quizId);
        } catch (IllegalArgumentException e) {
            expected = true;
        }
        assertTrue(expected);
    }

    @When("^I save it to the server$")
    public void I_save_it_to_the_server() throws Throwable {
        quizCreator.save(quizId);
        verify(mockQuizClient).save(eq(mockQuiz));
    }

    @Then("^I should be able to retrieve it$")
    public void I_should_be_able_to_retrieve_it() throws Throwable {
        List<Quiz> quizList = Arrays.asList(mockQuiz);
        when(mockQuizClient.getQuizList()).thenReturn(quizList);
        Quiz actual = quizCreator.getQuizzes().get(0);

        assertThat(mockQuiz, is(equalTo(actual)));
    }

    @When("^I try to save null to the server$")
    public void I_try_to_save_null_to_the_server() throws Throwable {
        quizCreator.save(quizId);
    }

    @Then("^the server should not be called$")
    public void the_server_should_not_be_called() throws Throwable {
        verify(mockQuizClient, never()).save(null);
    }
}
