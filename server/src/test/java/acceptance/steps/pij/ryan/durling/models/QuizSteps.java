package acceptance.steps.pij.ryan.durling.models;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizImpl;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.mock;

public class QuizSteps {

    private Quiz quiz;
    private Question question;

    @Given("^a user has a quiz named \"([^\"]*)\" and an (\\d+)$")
    public void a_user_has_a_quiz_named_and_an(String title, int id) throws Throwable {
        quiz = new QuizImpl(title, id);
        assertThat(quiz, instanceOf(Quiz.class));
    }

    @Then("^it should have a \"([^\"]*)\" and an (\\d+)$")
    public void it_should_have_a_and_an(String title, int id) throws Throwable {
        assertThat(quiz.getName(), is(equalTo(title)));
        assertThat(quiz.getId(), is(equalTo(id)));
    }

    @When("^a user adds a question$")
    public void a_user_adds_a_question() throws Throwable {
        question = mock(Question.class);
        quiz.add(question);
    }

    @Then("^the quiz should have the question$")
    public void the_quiz_should_have_the_question() throws Throwable {
        Set<Question> questions = new HashSet<>();
        questions.add(question);
        assertThat(quiz.getQuestions(), is(equalTo(questions)));
    }

    @Then("^the quiz should be \"([^\"]*)\"$")
    public void the_quiz_should_be(String value) throws Throwable {
        boolean valid = ifTrue(value);
        assertThat(quiz.valid(), is(equalTo(valid)));
    }

    private boolean ifTrue(String argument) {
        boolean value = false;
        String aTrueValue = "true";
        if (aTrueValue.equals(argument)) value = true;
        return value;
    }

}
