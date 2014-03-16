package pij.ryan.durling.registry;

import java.util.List;

public interface Quiz {
    String getName();

    int getId();

    List<Question> getQuestions();

    void addQuestion(Question question);
}
