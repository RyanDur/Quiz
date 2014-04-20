package acceptance.steps.pij.ryan.durling.resources;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.controllers.HighScoreCtrl;
import pij.ryan.durling.controllers.HighScoreCtrlImpl;
import pij.ryan.durling.controllers.QuizCtrl;
import pij.ryan.durling.resources.QuizMaster;
import pij.ryan.durling.resources.QuizMasterImpl;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class QuizPlaySteps {

    private QuizMaster quizMaster;
    private HighScoreCtrl highScoreCtrl;
    private QuizCtrl quizCtrl;

    @Given("^there is a Quiz play object$")
    public void there_is_a_Quiz_play_object() throws Throwable {
        quizCtrl = mock(QuizCtrl.class);
        highScoreCtrl = mock(HighScoreCtrlImpl.class);
        quizMaster = new QuizMasterImpl(quizCtrl, highScoreCtrl);
    }

    @When("^a user requests a set of available quizzes$")
    public void a_user_requests_a_set_of_available_quizzes() throws Throwable {
        quizMaster.getQuizOptions();
    }

    @Then("^a user should receive a list of available quizzes$")
    public void a_user_should_receive_a_list_of_available_quizzes() throws Throwable {
        verify(quizCtrl).getAvailableQuizzes();
    }

    @When("^a user request a (\\d+)$")
    public void a_user_request_a(int id) throws Throwable {
        quizMaster.getQuiz(id);
    }

    @Then("^a user should receive a quiz$")
    public void a_user_should_receive_a_quiz() throws Throwable {
        verify(quizCtrl).getQuiz(anyInt());
    }

    @When("^a user request a high score for a (\\d+)$")
    public void a_user_request_a_high_score_for_a(int quizId) throws Throwable {
        quizMaster.getHighScore(quizId);
    }

    @Then("^a user should receive a high score$")
    public void a_user_should_receive_a_high_score() throws Throwable {
        verify(highScoreCtrl).getHighScore(anyInt());
    }

    @When("^a \"([^\"]*)\" sets a high (\\d+) for a (\\d+)$")
    public void a_sets_a_high_for_a(String user, int score, int id) throws Throwable {
        quizMaster.setHighScore(id,user,score);
    }

    @Then("^a user should set the high score$")
    public void a_user_should_set_the_high_score() throws Throwable {
        verify(highScoreCtrl).setHighScore(anyInt(), anyString(), anyInt());
    }
}
