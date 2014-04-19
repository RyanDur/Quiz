package pij.ryan.durling.models;

import java.io.Serializable;

public class AnswerImpl implements Answer, Serializable {

    private String answer;
    private boolean correct;

    public AnswerImpl(String answer, boolean correct) {
        this.answer = answer;
        this.correct = correct;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean getValue() {
        return correct;
    }
}
