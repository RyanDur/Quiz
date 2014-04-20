package pij.ryan.durling.serializers;

import pij.ryan.durling.models.Quiz;

import java.util.concurrent.ConcurrentSkipListMap;

public interface QuizSerializer {

    /**
     * persist quizzes
     *
     * @param open open quizzes
     * @param closed closed quizzes
     */
    void persist(ConcurrentSkipListMap<Integer, Quiz> open, ConcurrentSkipListMap<Integer, Quiz> closed);

    /**
     * get the open quizzes
     *
     * @return list of available quizzes
     */
    ConcurrentSkipListMap<Integer, Quiz> getAvailable();

    /**
     * get the closed quizzes
     *
     * @return list of closed quizzes
     */
    ConcurrentSkipListMap<Integer, Quiz> getClosed();

    /**
     * check if data exists
     *
     * @return boolean
     */
    boolean dataExists();
}
