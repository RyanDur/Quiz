package acceptance.steps.pij.ryan.durling.factories;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.factories.IdGenerator;
import pij.ryan.durling.factories.QuizFactory;
import pij.ryan.durling.factories.QuizFactoryImpl;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuizFactorySteps {

    private QuizFactory quizFactory;
    private IdGenerator idGenerator;
    private Quiz quiz;
    private Question question;
    private Answer answer;

    @Given("^there is a quiz factory$")
    public void there_is_a_quiz_factory() throws Throwable {
        idGenerator = mock(IdGenerator.class);
        quizFactory = new QuizFactoryImpl(idGenerator);
    }

    @When("^a factory creates a quiz with (\\d+) and \"([^\"]*)\"$")
    public void a_factory_creates_a_quiz_with_and(int id, String title) throws Throwable {
        when(idGenerator.generateId()).thenReturn(id);
        quiz = quizFactory.createQuiz(title);
        assertThat(quiz, instanceOf(Quiz.class));
    }

    @Then("^it should return a quiz with (\\d+)$")
    public void it_should_return_a_quiz_with(int id) throws Throwable {
       assertThat(quiz.getId(), is(equalTo(id)));
    }

    @When("^a factory creates a \"([^\"]*)\" with a (\\d+)$")
    public void a_factory_creates_a_with_a(String questionString, int score) throws Throwable {
        question = quizFactory.createQuestion(questionString, score);
        assertThat(question, instanceOf(Question.class));
    }

    @Then("^it should return a \"([^\"]*)\" with a (\\d+)$")
    public void it_should_return_a_with_a(String questionString, int score) throws Throwable {
        assertThat(question.getQuestion(), is(equalTo(questionString)));
        assertThat(question.getValue(), is(equalTo(score)));
    }

    @When("^a factory creates an \"([^\"]*)\" with a \"([^\"]*)\"$")
    public void a_factory_creates_an_with_a(String answerString, String value) throws Throwable {
        boolean correct = ifTrue(value);
        answer = quizFactory.createAnswer(answerString, correct);
        assertThat(answer, instanceOf(Answer.class));
    }

    @Then("^it should return an \"([^\"]*)\" with a \"([^\"]*)\"$")
    public void it_should_return_an_with_a(String answerString, String value) throws Throwable {
        boolean correct = ifTrue(value);
        assertThat(answer.getAnswer(), is(equalTo(answerString)));
        assertThat(answer.getValue(), is(equalTo(correct)));
    }

    private boolean ifTrue(String argument) {
        boolean value = false;
        String aTrueValue = "true";
        if (aTrueValue.equals(argument)) value = true;
        return value;
    }
}
