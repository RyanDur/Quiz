package acceptance.steps.pij.ryan.durling.factories;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.factories.ScoreFactory;
import pij.ryan.durling.factories.ScoreFactoryImpl;
import pij.ryan.durling.models.Score;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScoreFactorySteps {

    private ScoreFactory scoreFactory;
    private Score scoreObj;

    @Given("^there is a score factory$")
    public void there_is_a_score_factory() throws Throwable {
        scoreFactory = new ScoreFactoryImpl();
    }

    @When("^a \"([^\"]*)\" creates a (\\d+)$")
    public void a_creates_a(String user, int score) throws Throwable {
        scoreObj = scoreFactory.createScore(user,score);
    }

    @Then("^it should return a score$")
    public void it_should_return_a_score() throws Throwable {
        assertThat(scoreObj, instanceOf(Score.class));
    }
}
