package pij.ryan.durling.controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import pij.ryan.durling.factories.ScoreFactory;
import pij.ryan.durling.models.Score;
import pij.ryan.durling.serializers.ScoreSerializer;

import java.rmi.RemoteException;
import java.util.concurrent.ConcurrentSkipListMap;

@Singleton
public class HighScoreCtrlImpl implements HighScoreCtrl {
    private ScoreFactory scoreFactory;
    private ConcurrentSkipListMap<Integer, Score> scores;

    @Inject
    public HighScoreCtrlImpl(ScoreFactory scoreFactory, ScoreSerializer serializer) {
        this.scoreFactory = scoreFactory;
        setupScores(serializer);
        serializer.persist(scores);
    }

    @Override
    public synchronized void setHighScore(int quizId, String player, int userScore) throws RemoteException {
        Score score = scoreFactory.createScore(player, userScore);
        scores.put(quizId, score);
    }

    @Override
    public synchronized Score getHighScore(int quizId) throws RemoteException {
        return scores.get(quizId);
    }

    private void setupScores(ScoreSerializer serializer) {
        if(serializer.dataExists()) {
            scores = serializer.getScores();
        } else {
            scores = new ConcurrentSkipListMap<>();
        }
    }
}
