package acceptance.steps.pij.ryan.durling.models;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.models.QuizOptionImpl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class QuizOptionSteps {

    private QuizOption option;

    @Given("^a user has an option with a \"([^\"]*)\" and an (\\d+)$")
    public void a_user_has_an_option_with_a_and_an(String title, int id) throws Throwable {
        option = new QuizOptionImpl(title, id);
        assertThat(option, instanceOf(QuizOption.class));
    }

    @Then("^it should have the \"([^\"]*)\" and (\\d+)$")
    public void it_should_have_the_and(String title, int id) throws Throwable {
        assertThat(option.getQuizTitle(), is(equalTo(title)));
        assertThat(option.getQuizId(), is(equalTo(id)));
    }
}
