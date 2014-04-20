package pij.ryan.durling.serializers;

import pij.ryan.durling.models.Score;

import java.util.concurrent.ConcurrentSkipListMap;

public interface ScoreSerializer {
    ConcurrentSkipListMap<Integer, Score> getScores();

    boolean dataExists();

    void persist(ConcurrentSkipListMap<Integer, Score> scores);
}
