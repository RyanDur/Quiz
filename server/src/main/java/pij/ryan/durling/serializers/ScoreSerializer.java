package pij.ryan.durling.serializers;

import pij.ryan.durling.models.Score;

import java.util.TreeMap;

public interface ScoreSerializer {
    TreeMap<Integer, Score> getScores();

    boolean dataExists();

    void persist(TreeMap<Integer, Score> scores);
}
