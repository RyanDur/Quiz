package pij.ryan.durling.models;

public class QuizOptionImpl implements QuizOption {
    private String title;
    private int id;

    public QuizOptionImpl(String title, int id) {
        this.title = title;
        this.id = id;
    }

    @Override
    public String getQuizTitle() {
        return title;
    }

    @Override
    public int getQuizId() {
        return id;
    }
}
