package pij.ryan.durling.models;

import java.util.Set;

public class QuizImpl implements Quiz {
    private String title;
    private int id;

    public QuizImpl(String title, int id) {
        this.title = title;
        this.id = id;
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean valid() {
        return false;
    }

    @Override
    public void add(Question question) {

    }

    @Override
    public Set<Question> getQuestions() {
        return null;
    }
}
