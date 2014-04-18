package acceptance.steps.pij.ryan.durling.models;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class ScoreSteps {

    @Given("^there is a score object for \"([^\"]*)\" with (\\d+)$")
    public void there_is_a_score_object_for_with(String name, String userScore) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @Then("^a player has a (\\d+)$")
    public void a_player_has_a(int userScore) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }
}
