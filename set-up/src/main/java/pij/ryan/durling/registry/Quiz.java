package pij.ryan.durling.registry;

public interface Quiz {
    String getName();

    int getId();

    boolean valid();

    boolean contains(Question question);

    void add(Question question);
}
