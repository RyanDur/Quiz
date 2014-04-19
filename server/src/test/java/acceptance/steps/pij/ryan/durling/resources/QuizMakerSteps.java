package acceptance.steps.pij.ryan.durling.resources;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.controllers.QuizCtrl;
import pij.ryan.durling.factories.QuizFactory;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.resources.QuizMaker;
import pij.ryan.durling.resources.QuizMakerImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class QuizMakerSteps {

    private QuizCtrl quizCtrl;
    private Quiz quiz;
    private Question question;
    private Answer answer;
    private QuizMaker quizMaker;
    private QuizFactory quizFactory;

    @Given("^there is a quiz maker$")
    public void there_is_a_quiz_maker() throws Throwable {
        quizCtrl = mock(QuizCtrl.class);
        quizFactory = mock(QuizFactory.class);
        quiz = mock(Quiz.class);
        question = mock(Question.class);
        answer = mock(Answer.class);
        when(quizFactory.createQuiz(anyString())).thenReturn(quiz);
        when(quizFactory.createQuestion(anyString(), anyInt())).thenReturn(question);
        when(quizFactory.createAnswer(anyString(), anyBoolean())).thenReturn(answer);

        quizMaker = new QuizMakerImpl(quizCtrl, quizFactory);
    }

    @When("^a user creates a quiz \"([^\"]*)\"$")
    public void a_user_creates_a_quiz(String title) throws Throwable {
        quizMaker.createQuiz(title);
    }

    @Then("^a quiz is made$")
    public void a_quiz_is_made() throws Throwable {
        verify(quizFactory).createQuiz(anyString());
    }

    @When("^a user adds a \"([^\"]*)\" with a (\\d+)$")
    public void a_user_adds_a_with_a(String questionString, int score) throws Throwable {
        quizMaker.addQuestion(questionString, score);
    }

    @Then("^a question should be added to the quiz$")
    public void a_question_should_be_added_to_the_quiz() throws Throwable {
        verify(quizFactory).createQuestion(anyString(), anyInt());
        verify(quiz).add(question);
    }

    @And("^a user adds an \"([^\"]*)\" with a \"([^\"]*)\"$")
    public void a_user_adds_an_with_a(String answerString, String value) throws Throwable {
        boolean corrext = ifTrue(value);
        quizMaker.addAnswer(answerString, corrext);
    }

    @Then("^a answer should be added to the question$")
    public void a_answer_should_be_added_to_the_question() throws Throwable {
        verify(quizFactory).createAnswer(anyString(), anyBoolean());
        verify(question).add(answer);
    }

    private boolean ifTrue(String argument) {
        boolean value = false;
        String aTrueValue = "true";
        if (aTrueValue.equals(argument)) value = true;
        return value;
    }

    @Then("^a user can save a quiz$")
    public void a_user_can_save_a_quiz() throws Throwable {
        quizMaker.save();
        verify(quizCtrl).add(quiz);
    }

    @Then("^a user can validate the quiz$")
    public void a_user_can_validate_the_quiz() throws Throwable {
        quizMaker.validQuiz();
        verify(quiz).valid();
    }

    @Then("^a user can get the name of the quiz$")
    public void a_user_can_get_the_name_of_the_quiz() throws Throwable {
        quizMaker.getName();
        verify(quiz).getName();
    }

    @Then("^a user can check if the quiz is \"([^\"]*)\"$")
    public void a_user_can_check_if_the_quiz_is(String value) throws Throwable {
        boolean empty = ifTrue(value);

        assertThat(quizMaker.empty(), is(equalTo(empty)));
    }
}
