package pij.ryan.durling.resources;

import com.google.inject.Inject;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class QuizServerImpl implements QuizServer {

    private final Server server;

    @Inject
    public QuizServerImpl(ServerLink serverLink) throws RemoteException, NotBoundException {
        server = serverLink.getServer();
    }

    @Override
    public Quiz createQuiz(String name) {
        return server.createQuiz(name);
    }

    @Override
    public Question createQuestion(String question, int score) {
        return server.createQuestion(question, score);
    }

    @Override
    public void save(Quiz quiz) {
        server.save(quiz);
    }

    @Override
    public Answer createAnswer(String answer, boolean value) {
        return server.createAnswer(answer, value);
    }

    @Override
    public void lock(int quizId) {
        server.lock(quizId);
    }

    @Override
    public void unlock(int quizId) {
        server.unlock(quizId);
    }
}
