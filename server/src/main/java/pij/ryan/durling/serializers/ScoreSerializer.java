package pij.ryan.durling.serializers;

import pij.ryan.durling.models.Score;

import java.util.concurrent.ConcurrentSkipListMap;

public interface ScoreSerializer {

    /**
     * get the score objects
     *
     * @return list of Scores
     */
    ConcurrentSkipListMap<Integer, Score> getScores();

    /**
     * check if datat is exists
     *
     * @return boolean
     */
    boolean dataExists();

    /**
     * persist a list of scores
     *
     * @param scores List of scores
     */
    void persist(ConcurrentSkipListMap<Integer, Score> scores);
}
