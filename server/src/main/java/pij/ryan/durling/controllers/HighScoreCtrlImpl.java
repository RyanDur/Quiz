package pij.ryan.durling.controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import pij.ryan.durling.factories.ScoreFactory;
import pij.ryan.durling.models.Score;

import java.rmi.RemoteException;
import java.util.TreeMap;

@Singleton
public class HighScoreCtrlImpl implements HighScoreCtrl {
    private ScoreFactory scoreFactory;
    private TreeMap<Integer, Score> scores;

    @Inject
    public HighScoreCtrlImpl(ScoreFactory scoreFactory) {
        this.scoreFactory = scoreFactory;
        scores = new TreeMap<>();
    }

    @Override
    public void setHighScore(int quizId, String player, int userScore) throws RemoteException {
        Score score = scoreFactory.createScore(player, userScore);
        scores.put(quizId, score);
    }

    @Override
    public Score getHighScore(int quizId) throws RemoteException {
        return scores.get(quizId);
    }
}
