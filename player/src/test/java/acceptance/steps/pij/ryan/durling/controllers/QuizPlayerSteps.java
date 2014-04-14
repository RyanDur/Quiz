package acceptance.steps.pij.ryan.durling.controllers;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.controllers.Menu;
import pij.ryan.durling.controllers.QuizElements;
import pij.ryan.durling.controllers.QuizPlayer;
import pij.ryan.durling.controllers.QuizPlayerImpl;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.resources.QuizServer;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class QuizPlayerSteps {

    private QuizPlayer quizPlayer;
    private Menu menu;
    private QuizServer server;
    private Quiz quiz;
    private QuizElements quizElements;
    private String name;

    @Given("^there is a quiz player$")
    public void there_is_a_quiz_player() throws Throwable {
        server = mock(QuizServer.class);
        quizElements = mock(QuizElements.class);
        quizPlayer = new QuizPlayerImpl(server, quizElements);
    }

    @Given("^a player has a menu$")
    public void a_player_has_a_menu() throws Throwable {
        Menu mockMenu = mock(Menu.class);
        when(quizElements.getMenu(anyList())).thenReturn(mockMenu);
        menu = quizPlayer.getMenu();

        verify(server).getQuizOptions();
        assertThat(menu, is(equalTo(mockMenu)));
    }

    @When("^a player chooses an available quiz (\\d+)$")
    public void a_player_chooses_an_available_quiz(int menuOption) throws Throwable {
        quiz = mock(Quiz.class);
        int quizId = 4;
        when(menu.getQuizId(anyInt())).thenReturn(quizId);
        when(server.getQuiz(anyInt())).thenReturn(quiz);
        quizPlayer.chooseQuiz(menuOption);

        verify(server).getQuiz(anyInt());
        verify(menu).getQuizId(anyInt());
    }

    @Then("^a player should be able to get the questions for that quiz$")
    public void a_player_should_be_able_to_get_the_questions_for_that_quiz() throws Throwable {
        Set<Question> expected = new HashSet<>();
        when(quiz.getQuestions()).thenReturn(expected);
        Set<Question> actual = quizPlayer.getQuestions();

        verify(quiz).getQuestions();
        assertThat(actual, is(equalTo(expected)));
    }

    @Then("^a player should be able to get the name for that quiz$")
    public void a_player_should_be_able_to_get_the_name_for_that_quiz() throws Throwable {
        String expected = "Bob";
        when(quiz.getName()).thenReturn(expected);
        String actual = quizPlayer.getName();

        verify(quiz).getName();
        assertThat(actual, is(equalTo(expected)));
    }

    @Given("^a player named \"([^\"]*)\"$")
    public void a_player_named(String name) throws Throwable {
        this.name = name;
    }

    @Then("^a player should be able to get there \"([^\"]*)\"$")
    public void a_player_should_be_able_to_get_there(String expected) throws Throwable {
        assertThat(quizPlayer.getPlayerName(), is(equalTo(expected)));
    }

    @When("^a player gives there \"([^\"]*)\"$")
    public void a_player_gives_there(String name) throws Throwable {
        quizPlayer.setPlayerName(name);
    }
}
