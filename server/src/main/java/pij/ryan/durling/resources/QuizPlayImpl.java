package pij.ryan.durling.resources;

import pij.ryan.durling.controllers.HighScoreCtrl;
import pij.ryan.durling.controllers.QuizCtrl;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.models.Score;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

public class QuizPlayImpl extends UnicastRemoteObject implements QuizPlay {
    private QuizCtrl quizCtrl;
    private HighScoreCtrl highSoreCtrl;

    public QuizPlayImpl(QuizCtrl quizCtrl, HighScoreCtrl highSoreCtrl) throws RemoteException {
        this.quizCtrl = quizCtrl;
        this.highSoreCtrl = highSoreCtrl;
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
    public Score getHighScore(int quizId) throws RemoteException {
        return highSoreCtrl.getHighScore(quizId);
    }

    @Override
    public void setHighScore(int quizId, String playerName, int score) throws RemoteException {
        highSoreCtrl.setHighScore(quizId, playerName, score);
    }
}
