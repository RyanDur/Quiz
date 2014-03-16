package pij.ryan.durling.registry;

import java.util.Set;

public interface Quiz {
    String getName();

    int getId();

    Set<Question> getQuestions();

    void addQuestion(Question question);
}
