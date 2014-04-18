package pij.ryan.durling.controllers;

import com.google.inject.Inject;
import pij.ryan.durling.factories.ScoreFactory;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.Score;

import java.rmi.RemoteException;
import java.util.TreeMap;

public class HighScoreCtrlImpl implements HighScoreCtrl {
    private ScoreFactory scoreFactory;
    private TreeMap<Integer, Score> scores;

    @Inject
    public HighScoreCtrlImpl(ScoreFactory scoreFactory) {
        this.scoreFactory = scoreFactory;
        scores = new TreeMap<>();
    }

    @Override
    public boolean checkHighScore(Quiz quiz, int score) throws RemoteException {
        boolean result = true;
        if(scores.containsKey(quiz.getId())) {
            Score current = scores.get(quiz.getId());
            result = current.getScore() < score;
        }
        return result;
    }

    @Override
    public void setHighScore(Quiz quiz, String player, int userScore) throws RemoteException {
        Score score = scoreFactory.createScore(player, userScore);
        scores.put(quiz.getId(), score);
    }
}
