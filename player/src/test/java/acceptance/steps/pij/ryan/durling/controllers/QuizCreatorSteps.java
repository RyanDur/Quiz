package acceptance.steps.pij.ryan.durling.controllers;

import acceptance.steps.pij.ryan.durling.factories.QuizClient;
import acceptance.steps.pij.ryan.durling.registry.Quiz;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class QuizCreatorSteps {

    private QuizCreator quizCreator;
    private int quizId;
    private QuizClient mockQuizClient;

    @Given("^I have a quiz creator$")
    public void I_have_a_quiz_creator() throws Throwable {
        mockQuizClient = mock(QuizClient.class);
        quizCreator = new QuizCreatorImpl(mockQuizClient);
    }

    @When("^I create a quiz named \"([^\"]*)\"$")
    public void I_create_a_quiz_named(String name) throws Throwable {
        int id = 3;
        Quiz mockQuiz = mock(Quiz.class);
        when(mockQuiz.getName()).thenReturn(name);
        when(mockQuiz.getId()).thenReturn(id);
        when(mockQuizClient.create(anyString())).thenReturn(mockQuiz);

        quizId = quizCreator.create(name);
        verify(mockQuizClient).create(anyString());
    }

    @Then("^I should have a quiz with the name \"([^\"]*)\"$")
    public void I_should_have_a_quiz_with_the_name(String expected) throws Throwable {
        Quiz actual = quizCreator.get(quizId);

        assertThat(actual.getName(), is(equalTo(expected)));
    }
}
