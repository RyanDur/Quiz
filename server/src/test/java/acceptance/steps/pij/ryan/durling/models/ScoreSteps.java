package acceptance.steps.pij.ryan.durling.models;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pij.ryan.durling.models.Score;
import pij.ryan.durling.models.ScoreImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ScoreSteps {

    private Score score;

    @Given("^there is a score object for \"([^\"]*)\" with (\\d+)$")
    public void there_is_a_score_object_for_with(String name, int userScore) throws Throwable {
        score = new ScoreImpl(name, userScore);
    }

    @Then("^a player has a (\\d+)$")
    public void a_player_has_a(int userScore) throws Throwable {
        assertThat(score.getScore(), is(equalTo(userScore)));
    }
}
