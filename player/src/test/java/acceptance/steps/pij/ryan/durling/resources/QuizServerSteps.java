package acceptance.steps.pij.ryan.durling.resources;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.resources.QuizServer;
import pij.ryan.durling.resources.QuizServerImpl;
import pij.ryan.durling.resources.Server;
import pij.ryan.durling.resources.ServerLink;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.*;

public class QuizServerSteps {

    private Server mockServer;
    private QuizServer quizServer;
    private Set<QuizOption> actual;
    private Set<QuizOption> options;
    private Quiz quiz;
    private Quiz quizServerQuiz;
    private boolean winner;

    @Given("^there is a link to the server$")
    public void there_is_a_link_to_the_server() throws Throwable {
        ServerLink serverLink = mock(ServerLink.class);
        mockServer = mock(Server.class);
        when(serverLink.getServer()).thenReturn(mockServer);
        quizServer = new QuizServerImpl(serverLink);
    }

    @When("^a user asks for the available quizzes$")
    public void a_user_asks_for_the_available_quizzes() throws Throwable {
        options = new HashSet<>();
        when(mockServer.getQuizOptions()).thenReturn(options);
        actual = quizServer.getQuizOptions();
        verify(mockServer).getQuizOptions();
    }

    @Then("^a user should receive a list of quizzes available$")
    public void a_user_should_receive_a_list_of_quizzes_available() throws Throwable {
        assertThat(actual, is(equalTo(options)));
    }

    @When("^a user asks for a quiz by (\\d+)$")
    public void a_user_asks_for_a_quiz_by(int id) throws Throwable {
        quiz = mock(Quiz.class);
        when(mockServer.getQuiz(anyInt())).thenReturn(quiz);
        quizServerQuiz = quizServer.getQuiz(id);
        verify(mockServer).getQuiz(id);
    }

    @Then("^a user should receive a quiz$")
    public void a_user_should_receive_a_quiz() throws Throwable {
        assertThat(quizServerQuiz, is(equalTo(quiz)));
    }

    @When("^a user submits a quiz$")
    public void a_user_submits_a_quiz() throws Throwable {
        when(mockServer.checkHighScore(eq(quiz), anyString())).thenReturn(true);
        winner = quizServer.checkHighScore(quiz, "Bob");
        verify(mockServer).checkHighScore(eq(quiz), anyString());
    }

    @Then("^it should check the high score$")
    public void it_should_check_the_high_score() throws Throwable {
        assertThat(winner, is(equalTo(true)));
    }
}
