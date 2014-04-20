package pij.ryan.durling.serializers;

import pij.ryan.durling.models.Quiz;

import java.util.TreeMap;

public interface QuizSerializer {


    void persist(TreeMap<Integer, Quiz> quizzes, TreeMap<Integer, Quiz> closed);

    TreeMap<Integer, Quiz> getAvailable();

    TreeMap<Integer, Quiz> getClosed();

    boolean dataExists();
}
