package acceptance.steps.pij.ryan.durling.models;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.QuestionImpl;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class QuestionSteps {

    private Question question;
    private Answer answer;

    @Given("^a user has a \"([^\"]*)\" and a (\\d+)$")
    public void a_user_has_a_and_a(String questionString, int score) throws Throwable {
        question = new QuestionImpl(questionString, score);
        assertThat(question, instanceOf(Question.class));
    }

    @Then("^the question should have that \"([^\"]*)\" and (\\d+)$")
    public void the_question_should_have_that_and(String questionString, int score) throws Throwable {
        assertThat(question.getQuestion(), is(equalTo(questionString)));
        assertThat(question.getValue(), is(equalTo(score)));
    }

    @When("^a user adds an \"([^\"]*)\"$")
    public void a_user_adds_an(String answerString) throws Throwable {
        answer = mock(Answer.class);
        when(answer.getAnswer()).thenReturn(answerString);
        question.add(answer);
    }

    @Then("^the question should have that \"([^\"]*)\"$")
    public void the_question_should_have_that(String answerString) throws Throwable {
        Set<Answer> answerSet = new HashSet<>();
        answerSet.add(answer);
        Set<Answer> actual = question.getAnswers();
        assertThat(actual, is(equalTo(answerSet)));
        assertThat(actual.toArray(new Answer[actual.size()])[0].getAnswer(), is(equalTo(answerString)));
    }

    @Then("^the question should be \"([^\"]*)\"$")
    public void the_question_should_be(String value) throws Throwable {
        boolean valid = ifTrue(value);
        assertThat(question.valid(), is(equalTo(valid)));
    }

    private boolean ifTrue(String argument) {
        boolean value = false;
        String aTrueValue = "true";
        if (aTrueValue.equals(argument)) value = true;
        return value;
    }
}
