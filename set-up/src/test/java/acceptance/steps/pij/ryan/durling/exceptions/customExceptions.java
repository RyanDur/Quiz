package acceptance.steps.pij.ryan.durling.exceptions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class customExceptions {

    private Throwable thrown;

    @Given("^a method throws an InvalidQuizException$")
    public void a_method_throws_an_InvalidQuizException() throws Throwable {
        try {
            throw new InvalidQuizException();
        } catch (InvalidQuizException e) {
            thrown = e;
        }
    }

    @Given("^a method throws an IllegalQuizCreationException$")
    public void a_method_throws_an_IllegalQuizCreationException() throws Throwable {
        try {
            throw new IllegalQuizCreationException();
        } catch (IllegalQuizCreationException e) {
            thrown = e;
        }
    }

    @Then("^it should say \"([^\"]*)\"$")
    public void it_should_say(String message) throws Throwable {
        assertThat(thrown.getMessage(), is(equalTo(message)));
    }
}
