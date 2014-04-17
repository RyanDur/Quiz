package pij.ryan.durling.resources;

import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Set;

public class QuizServerImpl implements QuizServer {


    private Server server;

    public QuizServerImpl(ServerLink serverLink) {
        try {
            server = serverLink.getServer();
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Set<QuizOption> getQuizOptions() {
        return server.getQuizOptions();
    }

    @Override
    public Quiz getQuiz(int quizId) {
        return server.getQuiz(quizId);
    }

    @Override
    public boolean checkHighScore(Quiz quiz, String userName) {
        return server.checkHighScore(quiz, userName);
    }
}
