package pij.ryan.durling.models;

public interface Question {
    String getQuestion();

    int getValue();

    void add(Answer answer);

    boolean valid();
}
