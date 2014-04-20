package pij.ryan.durling.serializers;

import pij.ryan.durling.models.Quiz;

import java.util.TreeMap;

public interface QuizSerializer {

    void serialize(TreeMap<Integer, Quiz> quizzes, TreeMap<Integer, Quiz> closed);

    void deserialize();

    TreeMap<Integer, Quiz> getQuizzes();

    TreeMap<Integer, Quiz> getClosed();

    boolean dataExists();
}
