package acceptance.steps.pij.ryan.durling.controllers;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pij.ryan.durling.controllers.QuizMenu;
import pij.ryan.durling.controllers.QuizMenuImpl;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.resources.QuizServer;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuizMenuSteps {
    private List<Quiz> quizzes;
    private Quiz quiz;
    private QuizServer mockQuizServer;
    private QuizMenu quizMenu;
    private Quiz quiz1;
    private Quiz quiz2;
    private Quiz quiz3;
    private Quiz quiz4;
    private Quiz quiz5;
    private Quiz quiz6;
    private Quiz quiz7;
    private int userChoice;

    @cucumber.api.java.en.Given("^there is a menu of quizzes$")
    public void there_is_a_menu_of_quizzes() throws Throwable {
        mockQuizServer = mock(QuizServer.class);
        quizMenu = new QuizMenuImpl(mockQuizServer);
        List<Quiz> quizList = getQuizzes();
        when(mockQuizServer.getQuizList()).thenReturn(quizList);

        quizzes = quizMenu.getQuizList();
    }

    @When("^a player chooses quiz (\\d+)$")
    public void a_player_chooses_quiz(int quizIndex) throws Throwable {
        userChoice = quizIndex - 1;
        quiz = quizMenu.getQuiz(quizIndex);
    }

    @Then("^a player should get to play the quiz$")
    public void a_player_should_get_to_play_the_quiz() throws Throwable {
        assertThat(quiz, is(equalTo(quizzes.get(userChoice))));
    }

    private List<Quiz> getQuizzes() {
        quiz1 = mock(Quiz.class);
        quiz2 = mock(Quiz.class);
        quiz3 = mock(Quiz.class);
        quiz4 = mock(Quiz.class);
        quiz5 = mock(Quiz.class);
        quiz6 = mock(Quiz.class);
        quiz7 = mock(Quiz.class);
        return Arrays.asList(quiz1, quiz2, quiz3, quiz4, quiz5, quiz6, quiz7);
    }
}
