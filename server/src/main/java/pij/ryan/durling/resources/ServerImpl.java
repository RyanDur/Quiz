package pij.ryan.durling.resources;

import com.google.inject.Inject;
import pij.ryan.durling.controllers.HighScoreCtrl;
import pij.ryan.durling.controllers.QuizCtrl;
import pij.ryan.durling.factories.QuizFactory;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

public class ServerImpl extends UnicastRemoteObject implements Server {
    private QuizCtrl quizCtrl;
    private HighScoreCtrl highSoreCtrl;
    private QuizFactory quizFactory;

    @Inject
    public ServerImpl(QuizCtrl quizCtrl, HighScoreCtrl highSoreCtrl, QuizFactory quizFactory) throws RemoteException {
        this.quizCtrl = quizCtrl;
        this.highSoreCtrl = highSoreCtrl;
        this.quizFactory = quizFactory;
    }

    @Override
    public Quiz createQuiz(String title) throws RemoteException {
        return quizFactory.createQuiz(title);
    }

    @Override
    public Question createQuestion(String question, int score) throws RemoteException {
        return quizFactory.createQuestion(question, score);
    }

    @Override
    public Answer createAnswer(String answer, boolean value) throws RemoteException {
        return quizFactory.createAnswer(answer, value);
    }

    @Override
    public void save(Quiz quiz) throws RemoteException {
        quizCtrl.add(quiz);
    }

    @Override
    public Set<QuizOption> getQuizOptions() throws RemoteException {
        return quizCtrl.getQuizOptions();
    }

    @Override
    public Quiz getQuiz(int id) throws RemoteException {
        return quizCtrl.getQuiz(id);
    }

    @Override
    public boolean checkHighScore(Quiz quiz, int score) throws RemoteException {
        return highSoreCtrl.checkHighScore(quiz, score);
    }

    @Override
    public void setHighScore(Quiz quiz, String playerName, int score) throws RemoteException {
        highSoreCtrl.setHighScore(quiz, playerName, score);
    }
}
