package acceptance.steps.pij.ryan.durling.controllers;

import cucumber.api.java.en.And;
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
    private int score;

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
        String actual = quizPlayer.getQuizName();

        verify(quiz).getName();
        assertThat(actual, is(equalTo(expected)));
    }

    @Then("^a player should be able to get there \"([^\"]*)\"$")
    public void a_player_should_be_able_to_get_there(String expected) throws Throwable {
        assertThat(quizPlayer.getPlayerName(), is(equalTo(expected)));
    }

    @When("^a player gives there \"([^\"]*)\"$")
    public void a_player_gives_there(String name) throws Throwable {
        quizPlayer.setPlayerName(name);
    }

    @And("^a player submits the quiz$")
    public void a_player_submits_the_quiz() throws Throwable {
        score = 52;
        when(server.checkHighScore(eq(quiz), anyString())).thenReturn(true);
        when(quiz.getScore()).thenReturn(score);
        quizPlayer.submitQuiz();

        verify(quiz).getScore();
    }

    @Then("^a player should be able to get the score for the quiz$")
    public void a_player_should_be_able_to_get_the_score_for_the_quiz() throws Throwable {
        assertThat(quizPlayer.getScore(), is(equalTo(score)));
    }

    @Then("^a player should be able to know if they have \"([^\"]*)\"$")
    public void a_player_should_be_able_to_know_if_they_have(String wonString) throws Throwable {
        boolean won = ifTrue(wonString);

        assertThat(quizPlayer.hasWon(), is(equalTo(won)));
        verify(server).checkHighScore(eq(quiz), anyString());
    }

    private boolean ifTrue(String argument) {
        boolean value = false;
        String aTrueValue = "true";
        if (aTrueValue.equals(argument)) value = true;
        return value;
    }
}
