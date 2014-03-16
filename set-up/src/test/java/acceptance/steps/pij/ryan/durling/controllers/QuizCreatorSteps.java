package acceptance.steps.pij.ryan.durling.controllers;

import cucumber.api.java.en.And;
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
    private QuizClient mockQuizClient = mock(QuizClient.class);
    private Quiz mockQuiz = mock(Quiz.class);
    private Question mockQuestion = mock(Question.class);
    private Integer quizId;
    private Throwable thrown;
    private String nullValue = "null";

    @Given("^a user has a quiz creator$")
    public void a_user_has_a_quiz_creator() throws Throwable {
        quizCreator = new QuizCreatorImpl(mockQuizClient);
    }

    @When("^a user creates a quiz named \"([^\"]*)\"$")
    public void a_user_creates_a_quiz_named(String name) throws Throwable {
        if (nullValue.equals(name)) name = null;
        int id = 3;

        when(mockQuiz.getName()).thenReturn(name);
        when(mockQuiz.getId()).thenReturn(id);
        when(mockQuizClient.createQuiz(anyString())).thenReturn(mockQuiz);

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
        verify(mockQuizClient).createQuiz(anyString());
        verify(mockQuiz).getId();
    }

    @Then("^a quiz should not be created$")
    public void a_quiz_should_not_be_created() throws Throwable {
        assertThat(thrown, is(instanceOf(IllegalArgumentException.class)));
        verify(mockQuizClient, never()).createQuiz(anyString());
    }

    @And("^not return the quiz ID$")
    public void not_return_the_quiz_ID() throws Throwable {
        assertThat(quizId, is(nullValue()));
        verify(mockQuiz, never()).getId();
    }

    @When("^a user adds a question like \"([^\"]*)\"$")
    public void a_user_adds_a_question_like(String question) throws Throwable {
        if (nullValue.equals(question)) question = null;

        List<Question> questions = Arrays.asList(mockQuestion);
        when(mockQuizClient.createQuestion(anyString())).thenReturn(mockQuestion);
        when(mockQuiz.getQuestions()).thenReturn(questions);
        when(mockQuestion.getQuestion()).thenReturn(question);

        try {
            quizCreator.addQuestion(question);
        } catch (IllegalArgumentException e) {
            thrown = e;
        }
    }

    @Then("^the quiz should have that \"([^\"]*)\"$")
    public void the_quiz_should_have_that(String expected) throws Throwable {
        Quiz quiz = quizCreator.getQuiz();
        Question actual = quiz.getQuestions().get(0);

        assertThat(actual.getQuestion(), is(equalTo(expected)));
        assertThat(thrown, is(not(instanceOf(IllegalArgumentException.class))));
        verify(mockQuizClient).createQuestion(anyString());
        verify(mockQuiz).addQuestion(eq(mockQuestion));
    }

    @Then("^a user should be able to retrieve it$")
    public void a_user_should_be_able_to_retrieve_it() throws Throwable {
        List<Quiz> quizList = Arrays.asList(mockQuiz);
        when(mockQuizClient.getQuizList()).thenReturn(quizList);
        Quiz actual = quizCreator.getQuizzes().get(0);

        assertThat(mockQuiz, is(equalTo(actual)));
    }

    @Then("^the question should not be added$")
    public void the_question_should_not_be_added() throws Throwable {
        assertThat(thrown, is(instanceOf(IllegalArgumentException.class)));
        verify(mockQuizClient, never()).createQuestion(anyString());
        verify(mockQuiz, never()).addQuestion(eq(mockQuestion));
    }

    @When("^a user saves the quiz$")
    public void a_user_saves_the_quiz() throws Throwable {
        quizCreator.save();
    }

    @Then("^the quiz should not be saved$")
    public void the_quiz_should_not_be_saved() throws Throwable {
        verify(mockQuizClient, never()).save(null);
    }

    @And("^return the quiz ID$")
    public void return_the_quiz_ID() throws Throwable {
        assertNotNull(quizId);
    }
}