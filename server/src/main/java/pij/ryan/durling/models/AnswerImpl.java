package pij.ryan.durling.models;

public class AnswerImpl implements Answer {

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
