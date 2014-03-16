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
    private QuizClient mockQuizClient = mock(QuizClient.class);
    private Quiz mockQuiz = mock(Quiz.class);
    private Question mockQuestion = mock(Question.class);

    @Given("^a user has a quiz creator$")
    public void a_user_has_a_quiz_creator() throws Throwable {
        quizCreator = new QuizCreatorImpl(mockQuizClient);
    }

    @When("^a user creates a quiz named \"([^\"]*)\"$")
    public void a_user_creates_a_quiz_named(String name) throws Throwable {
        int id = 3;
        when(mockQuiz.getName()).thenReturn(name);
        when(mockQuiz.getId()).thenReturn(id);
        when(mockQuizClient.createQuiz(anyString())).thenReturn(mockQuiz);

        quizId = quizCreator.create(name);
        verify(mockQuizClient).createQuiz(anyString());
    }

    @Then("^they should have a quiz with the name \"([^\"]*)\"$")
    public void they_should_have_a_quiz_with_the_name(String expected) throws Throwable {
        Quiz actual = quizCreator.get(quizId);

        assertThat(actual.getName(), is(equalTo(expected)));
    }

    @When("^a user adds a question like \"([^\"]*)\"$")
    public void a_user_adds_a_question_like(String question) throws Throwable {
        List<Question> questions = Arrays.asList(mockQuestion);
        when(mockQuizClient.createQuestion(anyString())).thenReturn(mockQuestion);
        when(mockQuiz.getQuestions()).thenReturn(questions);
        when(mockQuestion.getQuestion()).thenReturn(question);

        quizCreator.addQuestion(question, quizId);
        verify(mockQuizClient).createQuestion(anyString());
        verify(mockQuiz).addQuestion(eq(mockQuestion));
    }

    @Then("^the quiz should have that \"([^\"]*)\"$")
    public void the_quiz_should_have_that(String expected) throws Throwable {
        Quiz quiz = quizCreator.get(quizId);
        Question actual = quiz.getQuestions().get(0);

        assertThat(actual.getQuestion(), is(equalTo(expected)));
    }

    @When("^a user creates a quiz without a \"([^\"]*)\"$")
    public void a_user_creates_a_quiz_without_a(String name) throws Throwable {
        boolean expected = false;
        try {
            quizCreator.create(name);
        } catch (IllegalArgumentException e) {
            expected = true;
        }
        assertTrue(expected);
    }

    @Given("^a user creates a quiz named null$")
    public void a_user_creates_a_quiz_named_null() throws Throwable {
        boolean expected = false;
        try {
            quizCreator.create(null);
        } catch (IllegalArgumentException e) {
            expected = true;
        }
        assertTrue(expected);
    }

    @Given("^a user adds an empty question like \"([^\"]*)\"$")
    public void a_user_adds_an_empty_question_like(String question) throws Throwable {
        boolean expected = false;
        try {
            quizCreator.addQuestion(question, quizId);
        } catch (IllegalArgumentException e) {
            expected = true;
        }
        assertTrue(expected);
    }

    @Given("^a user tries to add a null question$")
    public void a_user_tries_to_add_a_null_question() throws Throwable {
        boolean expected = false;
        try {
            quizCreator.addQuestion(null, quizId);
        } catch (IllegalArgumentException e) {
            expected = true;
        }
        assertTrue(expected);
    }

    @Then("^a user should be able to retrieve it$")
    public void a_user_should_be_able_to_retrieve_it() throws Throwable {
        List<Quiz> quizList = Arrays.asList(mockQuiz);
        when(mockQuizClient.getQuizList()).thenReturn(quizList);
        Quiz actual = quizCreator.getQuizzes().get(0);

        assertThat(mockQuiz, is(equalTo(actual)));
    }

    @When("^a user saves the quiz$")
    public void a_user_saves_the_quiz() throws Throwable {
        quizCreator.save(quizId);
    }

    @Then("^the server should not be called$")
    public void the_server_should_not_be_called() throws Throwable {
        verify(mockQuizClient, never()).save(null);
    }

    @Then("^a quiz should not be created$")
    public void a_quiz_should_not_be_created() throws Throwable {
        verify(mockQuizClient, never()).createQuiz(anyString());
    }

    @When("^a user tries to save a null quiz$")
    public void a_user_tries_to_save_a_null_quiz() throws Throwable {
        int nonExistentQuizID = 1000;
        quizCreator.save(nonExistentQuizID);
    }

    @Then("^the question should not be added$")
    public void the_question_should_not_be_added() throws Throwable {
        verify(mockQuizClient, never()).createQuestion(anyString());
        verify(mockQuiz, never()).addQuestion(eq(mockQuestion));
    }
}
