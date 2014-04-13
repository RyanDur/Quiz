package pij.ryan.durling.exceptions;

import pij.ryan.durling.messages.CustomExceptionMessages;

public class InvalidQuizException extends Exception {
    public InvalidQuizException() {
        super(CustomExceptionMessages.INVALID_QUIZ);
    }
}
