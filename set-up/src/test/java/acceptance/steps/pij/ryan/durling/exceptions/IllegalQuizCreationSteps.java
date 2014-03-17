package acceptance.steps.pij.ryan.durling.exceptions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class IllegalQuizCreationSteps {

    private Throwable thrown;

    @Given("^a metod throws an IllegalQuizCreationException$")
    public void a_metod_throws_an_IllegalQuizCreationException() throws Throwable {
        try {
            throw new IllegalQuizCreationException();
        } catch (IllegalQuizCreationException e) {
            thrown = e;
        }
    }

    @Then("^it should have a meaningful message$")
    public void it_should_have_a_meaningful_message() throws Throwable {
        String message = "Need to create a quiz first.";
        assertThat(thrown, is(instanceOf(IllegalQuizCreationException.class)));
        assertThat(message, is(equalTo(thrown.getMessage())));
    }
}
