package pij.ryan.durling.models;

public interface Question {
    String getQuestion();

    int getValue();

    Answer createAnswer(String answerString, boolean correct);

    void add(Answer answer);

    boolean valid();
}
