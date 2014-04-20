package pij.ryan.durling.serializers;

import pij.ryan.durling.models.Quiz;

import java.util.concurrent.ConcurrentSkipListMap;

public interface QuizSerializer {

    void persist(ConcurrentSkipListMap<Integer, Quiz> quizzes, ConcurrentSkipListMap<Integer, Quiz> closed);

    ConcurrentSkipListMap<Integer, Quiz> getAvailable();

    ConcurrentSkipListMap<Integer, Quiz> getClosed();

    boolean dataExists();
}
