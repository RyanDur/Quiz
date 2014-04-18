package pij.ryan.durling.factories;

import pij.ryan.durling.models.Score;

public interface ScoreFactory {
    Score createScore(String playerName, int score);
}
