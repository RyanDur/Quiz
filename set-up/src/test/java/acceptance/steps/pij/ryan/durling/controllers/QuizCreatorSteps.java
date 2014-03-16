package acceptance.steps.pij.ryan.durling.controllers;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.controllers.QuizCreatorImpl;
import pij.ryan.durling.Client.QuizClient;
import pij.ryan.durling.registry.Question;
import pij.ryan.durling.registry.Quiz;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class QuizCreatorSteps {

    private QuizCreator quizCreator;
    private int quizId;
    private QuizClient mockQuizClient;
    private Quiz mockQuiz;

    @Given("^I have a quiz creator$")
    public void I_have_a_quiz_creator() throws Throwable {
        mockQuizClient = mock(QuizClient.class);
        quizCreator = new QuizCreatorImpl(mockQuizClient);
    }

    @When("^I create a quiz named \"([^\"]*)\"$")
    public void I_create_a_quiz_named(String name) throws Throwable {
        int id = 3;
        mockQuiz = mock(Quiz.class);
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

    @When("^I add a question like \"([^\"]*)\"$")
    public void I_add_a_question_like(String question) throws Throwable {
        Question mockQuestion = mock(Question.class);
        List<Question> questions = Arrays.asList(mockQuestion);
        when(mockQuizClient.createQuestion(anyString())).thenReturn(mockQuestion);
        when(mockQuiz.getQuestions()).thenReturn(questions);
        when(mockQuestion.getQuestion()).thenReturn(question);

        quizCreator.addQuestion(question, quizId);
        verify(mockQuizClient).createQuestion(anyString());
    }

    @Then("^the quiz should have that \"([^\"]*)\"$")
    public void the_quiz_should_have_that(String expected) throws Throwable {
        Quiz quiz = quizCreator.get(quizId);
        Question actual = quiz.getQuestions().get(0);

        assertThat(actual.getQuestion(), is(equalTo(expected)));
    }
}
