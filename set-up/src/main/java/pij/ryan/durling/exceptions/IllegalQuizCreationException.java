package pij.ryan.durling.exceptions;

import pij.ryan.durling.messages.CustomExceptionMessages;

public class IllegalQuizCreationException extends Exception {
    public IllegalQuizCreationException() {
        super(CustomExceptionMessages.ILLEGAL_QUIZ);
    }
}
