package pij.ryan.durling.serializers;

import pij.ryan.durling.models.Quiz;

import java.util.TreeMap;

public interface QuizSerializer {

    void serialize(TreeMap<Integer, Quiz> quizzes);

    void deserialize();

    TreeMap<Integer, Quiz> getQuizzes();

    boolean dataExists();
}
