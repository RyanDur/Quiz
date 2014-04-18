package pij.ryan.durling.factories;

import pij.ryan.durling.models.Score;
import pij.ryan.durling.models.ScoreImpl;

public class ScoreFactoryImpl implements ScoreFactory {
    @Override
    public Score createScore(String playerName, int score) {
        return new ScoreImpl(playerName, score);
    }
}
