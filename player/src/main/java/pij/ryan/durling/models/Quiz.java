package pij.ryan.durling.models;

import java.util.Set;

public interface Quiz {
    String getName();

    int getId();

    boolean valid();

    boolean contains(Question question);

    void add(Question question);

    Set<Question> getQuestions();
}
