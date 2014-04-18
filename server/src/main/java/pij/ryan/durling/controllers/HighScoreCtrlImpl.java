package pij.ryan.durling.controllers;

import pij.ryan.durling.factories.ScoreFactory;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.Score;

import java.util.TreeMap;

public class HighScoreCtrlImpl implements HighScoreCtrl {
    private ScoreFactory scoreFactory;
    private TreeMap<Integer, Score> scores;

    public HighScoreCtrlImpl(ScoreFactory scoreFactory) {
        this.scoreFactory = scoreFactory;
        scores = new TreeMap<>();
    }

    @Override
    public boolean checkHighScore(Quiz quiz, int score) {
        boolean result = true;
        if(scores.containsKey(quiz.getId())) {
            Score current = scores.get(quiz.getId());
            result = current.getScore() < score;
        }
        return result;
    }

    @Override
    public void setHighScore(Quiz quiz, String player, int userScore) {
        Score score = scoreFactory.createScore(player, userScore);
        scores.put(quiz.getId(), score);
    }
}
