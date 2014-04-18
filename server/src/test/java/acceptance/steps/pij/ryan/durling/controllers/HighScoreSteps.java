package acceptance.steps.pij.ryan.durling.controllers;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.controllers.HighScoreCtrl;
import pij.ryan.durling.controllers.HighScoreCtrlImpl;
import pij.ryan.durling.factories.ScoreFactory;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.Score;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class HighScoreSteps {

    private HighScoreCtrl highScoreCtrl;
    private boolean result;
    private ScoreFactory scoreFactory;
    private Score score = mock(Score.class);
    private Quiz quiz = mock(Quiz.class);
    private String user;

    @Given("^there is a high score controller$")
    public void there_is_a_high_score_controller() throws Throwable {
        scoreFactory = mock(ScoreFactory.class);
        when(scoreFactory.createScore(anyString(), anyInt())).thenReturn(score);
        highScoreCtrl = new HighScoreCtrlImpl(scoreFactory);
    }

    @Then("^a user can check if there (\\d+) beats the (\\d+)$")
    public void a_user_can_check_if_there_beats_the(int playerScore, int current) throws Throwable {
        when(score.getScore()).thenReturn(current);
        result = highScoreCtrl.checkHighScore(quiz, playerScore);
    }

    @Then("^a user receives the \"([^\"]*)\"$")
    public void a_user_receives_the(String resultString) throws Throwable {
        boolean value = ifTrue(resultString);
        assertThat(result, is(equalTo(value)));
    }

    private boolean ifTrue(String argument) {
        boolean value = false;
        String aTrueValue = "true";
        if (aTrueValue.equals(argument)) value = true;
        return value;
    }

    @When("^a \"([^\"]*)\" has a quiz (\\d+)$")
    public void a_has_a_quiz(String name, int id) throws Throwable {
        user = name;
        when(quiz.getId()).thenReturn(id);
    }

    @Then("^a user sets there (\\d+)$")
    public void a_user_sets_there(int score) throws Throwable {
        highScoreCtrl.setHighScore(quiz, user, score);
        verify(scoreFactory).createScore(user, score);
    }
}
