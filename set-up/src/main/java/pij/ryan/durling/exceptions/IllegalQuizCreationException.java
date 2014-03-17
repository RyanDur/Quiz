package pij.ryan.durling.exceptions;

public class IllegalQuizCreationException extends Exception {
    public IllegalQuizCreationException() {
        super("Need to create a quiz first.");
    }
}
