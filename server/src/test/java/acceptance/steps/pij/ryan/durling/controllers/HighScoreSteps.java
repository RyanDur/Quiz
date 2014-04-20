package acceptance.steps.pij.ryan.durling.controllers;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.controllers.HighScoreCtrl;
import pij.ryan.durling.controllers.HighScoreCtrlImpl;
import pij.ryan.durling.factories.ScoreFactory;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.Score;
import pij.ryan.durling.serializers.ScoreSerializer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
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
    private Score score1;

    @Given("^there is a high score controller$")
    public void there_is_a_high_score_controller() throws Throwable {
        scoreFactory = mock(ScoreFactory.class);
        when(scoreFactory.createScore(anyString(), anyInt())).thenReturn(score);
        ScoreSerializer scoreSerializer = mock(ScoreSerializer.class);
        highScoreCtrl = new HighScoreCtrlImpl(scoreFactory,scoreSerializer);
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
        highScoreCtrl.setHighScore(quiz.getId(), user, score);
        verify(scoreFactory).createScore(user, score);
    }

    @And("^a user gets the score via the quiz (\\d+)$")
    public void a_user_gets_the_score_via_the_quiz(int id) throws Throwable {
        score1 = highScoreCtrl.getHighScore(id);
    }

    @Then("^a user should receive the score$")
    public void a_user_should_receive_the_score() throws Throwable {
        assertThat(score1, instanceOf(Score.class));
    }
}
