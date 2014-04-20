package acceptance.steps.pij.ryan.durling.resources;

import cucumber.api.java.en.Given;
import pij.ryan.durling.controllers.HighScoreCtrl;
import pij.ryan.durling.controllers.QuizCtrl;
import pij.ryan.durling.factories.QuizFactory;

import static org.mockito.Mockito.mock;

public class ServerSteps {

    private Server server;

    @Given("^there is a server$")
    public void there_is_a_server() throws Throwable {
        HighScoreCtrl highSoreCtrl = mock(HighScoreCtrl.class);
        QuizFactory quizFactory = mock(QuizFactory.class);
        QuizCtrl quizCtrl = mock(QuizCtrl.class);
        server = new ServerImpl(quizCtrl, highSoreCtrl, quizFactory);
    }

    private boolean ifTrue(String argument) {
        boolean value = false;
        String aTrueValue = "true";
        if (aTrueValue.equals(argument)) value = true;
        return value;
    }
}
