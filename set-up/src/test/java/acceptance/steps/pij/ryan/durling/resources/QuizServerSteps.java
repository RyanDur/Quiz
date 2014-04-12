package acceptance.steps.pij.ryan.durling.resources;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.resources.QuizServer;
import pij.ryan.durling.resources.QuizServerImpl;
import pij.ryan.durling.resources.Server;
import pij.ryan.durling.resources.ServerLink;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class QuizServerSteps {

    private Server mockServer;
    private QuizServer quizServer;
    private Quiz quizServerQuiz;
    private Quiz mockQuiz;
    private Question quizServerQuestion;
    private Question mockQuestion;
    private Answer quizAnswer;
    private Answer mockAnswer;

    @Given("^a user has a Quiz Server$")
    public void a_user_has_a_Quiz_Server() throws Throwable {
        ServerLink mockServerLink = mock(ServerLink.class);
        mockServer = mock(Server.class);
        when(mockServerLink.getServer()).thenReturn(mockServer);
        quizServer = new QuizServerImpl(mockServerLink);
    }

    @When("^a user creates a quiz the the \"([^\"]*)\"$")
    public void a_user_creates_a_quiz_the_the(String name) throws Throwable {
        mockQuiz = mock(Quiz.class);
        when(mockQuiz.getName()).thenReturn(name);
        when(mockServer.createQuiz(anyString())).thenReturn(mockQuiz);

        quizServerQuiz = quizServer.createQuiz(name);
    }

    @Then("^the server should return a quiz$")
    public void the_server_should_return_a_quiz() throws Throwable {
        assertThat(quizServerQuiz, is(equalTo(mockQuiz)));
    }

    @When("^a user creates a question \"([^\"]*)\" with a score of (.*)$")
    public void a_user_creates_a_question_with_a_score_of(String question, String score) throws Throwable {
        mockQuestion = mock(Question.class);
        when(mockServer.createQuestion(anyString(), anyInt())).thenReturn(mockQuestion);

        quizServerQuestion = quizServer.createQuestion(question, Integer.parseInt(score));
    }

    @Then("^the server should return a question$")
    public void the_server_should_return_a_question() throws Throwable {
        assertThat(quizServerQuestion, is(equalTo(mockQuestion)));
    }

    @Then("^a user can save a quiz$")
    public void a_user_can_save_a_quiz() throws Throwable {
        quizServer.save(quizServerQuiz);
        verify(mockServer).save(quizServerQuiz);
    }

    @When("^a user create an answer \"([^\"]*)\" with a \"([^\"]*)\"$")
    public void a_user_create_an_answer_with_a(String answer, String stringValue) throws Throwable {
        boolean value = ifTrue(stringValue);
        mockAnswer = mock(Answer.class);
        when(mockServer.createAnswer(anyString(), anyBoolean())).thenReturn(mockAnswer);

        quizAnswer = quizServer.createAnswer(answer, value);
    }

    private boolean ifTrue(String argument) {
        boolean value = false;
        String aTrueValue = "true";
        if (aTrueValue.equals(argument)) value = true;
        return value;
    }

    @Then("^the user should have an answer$")
    public void the_user_should_have_an_answer() throws Throwable {
        assertThat(quizAnswer, is(equalTo(mockAnswer)));
    }

    @Then("^a user can lock a quiz with an id of (\\d+)$")
    public void a_user_can_lock_a_quiz_with_an_id_of(String id) throws Throwable {
        quizServer.lock(Integer.parseInt(id));
        verify(mockServer).lock(anyInt());
    }
}
