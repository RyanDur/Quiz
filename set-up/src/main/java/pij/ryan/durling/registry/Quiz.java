package pij.ryan.durling.registry;

public interface Quiz {
    String getName();

    int getId();

    Question createQuestion(String questionString, int value);

    boolean isInValid();

    boolean contains(Question question);
}
