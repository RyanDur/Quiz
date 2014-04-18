package acceptance.steps.pij.ryan.durling.factories;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.factories.OptionFactory;
import pij.ryan.durling.factories.OptionFactoryImpl;
import pij.ryan.durling.models.QuizOption;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class OptionFactorySteps {

    private OptionFactory optionFactory;
    private QuizOption option;

    @Given("^there is a quiz option factory$")
    public void there_is_a_quiz_option_factory() throws Throwable {
        optionFactory = new OptionFactoryImpl();
    }

    @When("^a user creates an option with \"([^\"]*)\" and (\\d+)$")
    public void a_user_creates_an_option_with_and(String title, int id) throws Throwable {
        option = optionFactory.createQuizOption(id, title);
    }

    @Then("^there should be a quiz option$")
    public void there_should_be_a_quiz_option() throws Throwable {
        assertThat(option, instanceOf(QuizOption.class));
    }
}
