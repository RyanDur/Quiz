package acceptance.steps.pij.ryan.durling.resources;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.controllers.QuizCtrl;
import pij.ryan.durling.factories.QuizFactory;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.resources.Server;
import pij.ryan.durling.resources.ServerImpl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class ServerSteps {

    private Server server;
    private QuizCtrl quizCtrl;
    private Quiz quiz;
    private Quiz mockQuiz;
    private Question question;
    private Question mockQuestion;
    private QuizFactory quizFactory;
    private Answer answer;
    private Answer mockAnswer;

    @Given("^there is a server$")
    public void there_is_a_server() throws Throwable {
        quizFactory = mock(QuizFactory.class);
        quizCtrl = mock(QuizCtrl.class);
        server = new ServerImpl(quizCtrl, quizFactory);
    }

    @When("^a user creates a quiz named \"([^\"]*)\"$")
    public void a_user_creates_a_quiz_named(String title) throws Throwable {
        mockQuiz = mock(Quiz.class);
        when(mockQuiz.getName()).thenReturn(title);
        when(quizFactory.createQuiz(anyString())).thenReturn(mockQuiz);
        quiz = server.createQuiz(title);
        verify(quizFactory).createQuiz(title);
    }

    @Then("^the user should receive a quiz$")
    public void the_user_should_receive_a_quiz() throws Throwable {
        assertThat(quiz, is(equalTo(mockQuiz)));
    }

    @When("^a user creates a \"([^\"]*)\" with a (\\d+)$")
    public void a_user_creates_a_with_a(String questionString, int score) throws Throwable {
        mockQuestion = mock(Question.class);
        when(quizFactory.createQuestion(questionString, score)).thenReturn(mockQuestion);
        question = server.createQuestion(questionString, score);
        verify(quizFactory).createQuestion(anyString(), anyInt());
    }

    @Then("^the user should receive a question$")
    public void the_user_should_receive_a_question() throws Throwable {
        assertThat(question, is(equalTo(mockQuestion)));
    }

    @When("^a user creates a \"([^\"]*)\" \"([^\"]*)\"$")
    public void a_user_creates_a(String correct, String answerString) throws Throwable {
        boolean value = ifTrue(correct);
        mockAnswer = mock(Answer.class);
        when(quizFactory.createAnswer(answerString, value)).thenReturn(mockAnswer);
        answer = server.createAnswer(answerString, value);
        verify(quizFactory).createAnswer(anyString(), anyBoolean());
    }

    @Then("^the user should receive an answer$")
    public void the_user_should_receive_an_answer() throws Throwable {
        assertThat(answer, is(equalTo(mockAnswer)));
    }

    private boolean ifTrue(String argument) {
        boolean value = false;
        String aTrueValue = "true";
        if (aTrueValue.equals(argument)) value = true;
        return value;
    }
}
