package acceptance.features.pij.ryan.durling.models;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizImpl;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class QuizSteps {
    @Given("^a user has a quiz named \"([^\"]*)\" and an (\\d+)$")
    public void a_user_has_a_quiz_named_and_an(String title, int id) throws Throwable {
        Quiz quiz = new QuizImpl(title, id);
        assertThat(quiz, instanceOf(Quiz.class));
    }

    @Then("^it should have a \"([^\"]*)\" and an (\\d+)$")
    public void it_should_have_a_and_an(String title, int id) throws Throwable {

    }
}
