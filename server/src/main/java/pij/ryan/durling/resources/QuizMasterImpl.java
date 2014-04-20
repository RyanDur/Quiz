package pij.ryan.durling.resources;

import com.google.inject.Inject;
import pij.ryan.durling.controllers.HighScoreCtrl;
import pij.ryan.durling.controllers.QuizCtrl;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.models.Score;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

public class QuizMasterImpl extends UnicastRemoteObject implements QuizMaster {
    private QuizCtrl quizCtrl;
    private HighScoreCtrl highSoreCtrl;

    @Inject
    public QuizMasterImpl(QuizCtrl quizCtrl, HighScoreCtrl highSoreCtrl) throws RemoteException {
        this.quizCtrl = quizCtrl;
        this.highSoreCtrl = highSoreCtrl;
    }

    @Override
    public synchronized Set<QuizOption> getQuizOptions() throws RemoteException {
        return quizCtrl.getAvailableQuizzes();
    }

    @Override
    public synchronized Quiz getQuiz(int id) throws RemoteException {
        return quizCtrl.getQuiz(id);
    }

    @Override
    public synchronized Score getHighScore(int quizId) throws RemoteException {
        return highSoreCtrl.getHighScore(quizId);
    }

    @Override
    public synchronized void setHighScore(int quizId, String playerName, int score) throws RemoteException {
        highSoreCtrl.setHighScore(quizId, playerName, score);
    }
}
