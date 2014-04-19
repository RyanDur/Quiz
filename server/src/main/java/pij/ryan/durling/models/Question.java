package pij.ryan.durling.models;

import java.util.Set;

public interface Question {
    String getQuestion();

    int getValue();

    void add(Answer answer);

    boolean valid();

    Set<Answer> getAnswers();
}
