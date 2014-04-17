package pij.ryan.durling.models;

import java.util.HashSet;
import java.util.Set;

public class QuizImpl implements Quiz {
    private String title;
    private int id;
    private final Set<Question> questions;

    public QuizImpl(String title, int id) {
        this.title = title;
        this.id = id;
        questions = new HashSet<>();
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
        return !questions.isEmpty();
    }

    @Override
    public void add(Question question) {
        questions.add(question);
    }

    @Override
    public Set<Question> getQuestions() {
        return questions;
    }
}
