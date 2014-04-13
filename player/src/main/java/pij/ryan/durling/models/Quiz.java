package pij.ryan.durling.models;

public interface Quiz {
    String getName();

    int getId();

    boolean valid();

    boolean contains(Question question);

    void add(Question question);
}
