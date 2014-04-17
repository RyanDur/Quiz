package acceptance.steps.pij.ryan.durling.models;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.AnswerImpl;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class AnswerSteps {

    private Answer answer;

    @Given("^a user has a \"([^\"]*)\" \"([^\"]*)\"$")
    public void a_user_has_a(String correctString , String answerString) throws Throwable {
        boolean correct = ifTrue(correctString);
        answer = new AnswerImpl(answerString, correct);
        assertThat(answer, instanceOf(Answer.class));
    }

    @Then("^it should be \"([^\"]*)\"$")
    public void it_should_be(String value) throws Throwable {
        boolean correct = ifTrue(value);
        assertThat(answer.getValue(), is(equalTo(correct)));
    }

    @And("^it should have an \"([^\"]*)\"$")
    public void it_should_have_an(String answerString) throws Throwable {
        assertThat(answer.getAnswer(), is(equalTo(answerString)));
    }

    private boolean ifTrue(String argument) {
        boolean value = false;
        String aTrueValue = "true";
        if (aTrueValue.equals(argument)) value = true;
        return value;
    }

    private String ifNull(String argument) {
        String nullValue = "null";
        if (nullValue.equals(argument)) argument = null;
        return argument;
    }
}
