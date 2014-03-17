package pij.ryan.durling.registry;

public interface Question {
    String getQuestion();

    int getValue();

    Answer createAnswer(String answerString, boolean correct);
}
