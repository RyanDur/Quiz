package pij.ryan.durling.serializers;

import pij.ryan.durling.models.Score;

import java.util.TreeMap;

public interface ScoreSerializer {

    void serialize(TreeMap<Integer, Score> scores);

    void deserialize();

    TreeMap<Integer, Score> getScores();

    boolean dataExists();
}
