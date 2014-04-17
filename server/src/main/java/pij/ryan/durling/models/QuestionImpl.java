package pij.ryan.durling.models;

import java.util.HashSet;
import java.util.Set;

public class QuestionImpl implements Question {
    private String question;
    private int score;
    private final Set<Answer> answers;

    public QuestionImpl(String question, int score) {
        this.question = question;
        this.score = score;
        answers = new HashSet<>();
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public int getValue() {
        return score;
    }

    @Override
    public void add(Answer answer) {
        answers.add(answer);
    }

    @Override
    public boolean valid() {
        return !answers.isEmpty();
    }

    @Override
    public Set<Answer> getAnswers() {
        return answers;
    }
}
