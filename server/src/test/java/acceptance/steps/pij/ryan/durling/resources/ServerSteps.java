package acceptance.steps.pij.ryan.durling.resources;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.controllers.HighScoreCtrl;
import pij.ryan.durling.controllers.QuizCtrl;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.resources.Server;
import pij.ryan.durling.resources.ServerImpl;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class ServerSteps {

    private Server server;
    private QuizCtrl quizCtrl;
    private Quiz quiz;
    private Quiz mockQuiz;
    private Question question;
    private Question mockQuestion;
    private Answer answer;
    private Answer mockAnswer;
    private Set<QuizOption> mockOptions;
    private Set<QuizOption> options;
    private HighScoreCtrl highSoreCtrl;

    @Given("^there is a server$")
    public void there_is_a_server() throws Throwable {
        highSoreCtrl = mock(HighScoreCtrl.class);
        quizCtrl = mock(QuizCtrl.class);
        server = new ServerImpl(quizCtrl, highSoreCtrl);
    }

    @When("^a user creates a quiz named \"([^\"]*)\"$")
    public void a_user_creates_a_quiz_named(String title) throws Throwable {
        mockQuiz = mock(Quiz.class);
        when(mockQuiz.getName()).thenReturn(title);
        when(quizCtrl.createQuiz(anyString())).thenReturn(mockQuiz);
        quiz = server.createQuiz(title);
        verify(quizCtrl).createQuiz(title);
    }

    @When("^a user creates a \"([^\"]*)\" with a (\\d+)$")
    public void a_user_creates_a_with_a(String questionString, int score) throws Throwable {
        mockQuestion = mock(Question.class);
        when(quizCtrl.createQuestion(questionString, score)).thenReturn(mockQuestion);
        question = server.createQuestion(questionString, score);
        verify(quizCtrl).createQuestion(anyString(), anyInt());
    }

    @Then("^the user should receive a question$")
    public void the_user_should_receive_a_question() throws Throwable {
        assertThat(question, is(equalTo(mockQuestion)));
    }

    @When("^a user creates a \"([^\"]*)\" \"([^\"]*)\"$")
    public void a_user_creates_a(String correct, String answerString) throws Throwable {
        boolean value = ifTrue(correct);
        mockAnswer = mock(Answer.class);
        when(quizCtrl.createAnswer(answerString, value)).thenReturn(mockAnswer);
        answer = server.createAnswer(answerString, value);
        verify(quizCtrl).createAnswer(anyString(), anyBoolean());
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

    @Then("^the user can save a quiz$")
    public void the_user_can_save_a_quiz() throws Throwable {
        server.save(quiz);
        verify(quizCtrl).add(quiz);
    }

    @When("^a user asks for the available quizzes$")
    public void a_user_asks_for_the_available_quizzes() throws Throwable {
        mockOptions = new HashSet<>();
        when(quizCtrl.getQuizOptions()).thenReturn(mockOptions);
        options = server.getQuizOptions();
        verify(quizCtrl).getQuizOptions();
    }

    @Then("^a user receives a list of available quizzes$")
    public void a_user_receives_a_list_of_available_quizzes() throws Throwable {
        assertThat(options, is(equalTo(mockOptions)));
    }

    @When("^a user asks for quiz (\\d+)$")
    public void a_user_asks_for_quiz(int id) throws Throwable {
        when(quizCtrl.getQuiz(anyInt())).thenReturn(mockQuiz);
        quiz = server.getQuiz(id);
        verify(quizCtrl).getQuiz(anyInt());
    }

    @Then("^a user receives a quiz$")
    public void a_user_receives_a_quiz() throws Throwable {
        assertThat(quiz, is(equalTo(mockQuiz)));
    }

    @And("^a user checks if he has the high (\\d+)$")
    public void a_user_checks_if_he_has_the_high(int score) throws Throwable {
        server.checkHighScore(quiz, score);
    }

    @Then("^the user receives a notification$")
    public void the_user_receives_a_notification() throws Throwable {
        verify(highSoreCtrl).checkHighScore(eq(quiz), anyInt());
    }

    @Then("^a user set the high (\\d+)$")
    public void a_user_set_the_high(int score) throws Throwable {
        server.setHighScore(quiz, "Bob", score);
        verify(highSoreCtrl).setHighScore(eq(quiz), anyString(), anyInt());
    }
}
