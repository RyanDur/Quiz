package acceptance.steps.pij.ryan.durling.controllers;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.controllers.QuizCtrl;
import pij.ryan.durling.controllers.QuizCtrlImpl;
import pij.ryan.durling.factories.OptionFactory;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.serializers.QuizSerializer;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class QuizCtrlSteps {

    private QuizCtrl quizCtrl;
    private Quiz quiz;
    private OptionFactory optionFactory;
    private QuizOption option;

    @Given("^a user has a quiz controller$")
    public void a_user_has_a_quiz_controller() throws Throwable {
        optionFactory = mock(OptionFactory.class);
        QuizSerializer quizSerializer = mock(QuizSerializer.class);
        quizCtrl = new QuizCtrlImpl(optionFactory, quizSerializer);
    }

    @And("^a user has a (\\d+)$")
    public void a_user_has_a(int id) throws Throwable {
        quiz = mock(Quiz.class);
        option = mock(QuizOption.class);
        when(quiz.getId()).thenReturn(id);
    }

    @When("^a user adds a quiz$")
    public void a_user_adds_a_quiz() throws Throwable {
        quizCtrl.add(quiz);
    }

    @Then("^the the user can retrieve the (\\d+)$")
    public void the_the_user_can_retrieve_the(int id) throws Throwable {
        assertThat(quizCtrl.getQuiz(id), is(equalTo(quiz)));
    }

    @Then("^the the user can retrieve list of the available (\\d+)$")
    public void the_the_user_can_retrieve_list_of_the_available_quiz(int id) throws Throwable {
        when(optionFactory.createQuizOption(quiz.getId(), quiz.getName())).thenReturn(option);
        Set<QuizOption> options = new HashSet<>();
        options.add(option);
        when(option.getQuizId()).thenReturn(id);

        Set<QuizOption> quizOptions = quizCtrl.getQuizOptions();
        verify(optionFactory).createQuizOption(anyInt(), anyString());
        assertThat(quizOptions, is(equalTo(options)));
        assertThat(quizOptions.toArray(new QuizOption[quizOptions.size()])[0].getQuizId(), is(equalTo(id)));
    }

    @When("^a user close a (\\d+)$")
    public void a_user_close_a(int quizId) throws Throwable {
        quizCtrl.close(quizId);
    }

    @Then("^the quiz should be closed$")
    public void the_quiz_should_be_closed() throws Throwable {
        when(optionFactory.createQuizOption(quiz.getId(), quiz.getName())).thenReturn(option);
        Set<QuizOption> open = new HashSet<>();
        Set<QuizOption> closed = new HashSet<>();
        closed.add(option);

        assertThat(quizCtrl.getClosedOptions(), is(equalTo(closed)));
        assertThat(quizCtrl.getQuizOptions(), is(equalTo(open)));
    }


    @When("^a user opens a (\\d+)$")
    public void a_user_opens_a(int id) throws Throwable {
        quizCtrl.open(id);
    }

    @Then("^the quiz should be opened$")
    public void the_quiz_should_be_opened() throws Throwable {
        when(optionFactory.createQuizOption(quiz.getId(), quiz.getName())).thenReturn(option);
        Set<QuizOption> closed = new HashSet<>();
        Set<QuizOption> open = new HashSet<>();
        open.add(option);

        assertThat(quizCtrl.getClosedOptions(), is(equalTo(closed)));
        assertThat(quizCtrl.getQuizOptions(), is(equalTo(open)));
    }

}
