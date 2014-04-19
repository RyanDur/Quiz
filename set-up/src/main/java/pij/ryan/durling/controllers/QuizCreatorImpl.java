package pij.ryan.durling.controllers;

import com.google.inject.Inject;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;
import pij.ryan.durling.messages.ControllerMessages;
import pij.ryan.durling.resources.QuizMaker;
import pij.ryan.durling.resources.ServerLink;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class QuizCreatorImpl implements QuizCreator {

    private QuizMaker quizMaker;
    private int quizId;

    @Inject
    public QuizCreatorImpl(ServerLink serverLink) {
        try {
            quizMaker = serverLink.getQuizMaker();
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return quizMaker.getName();
    }

    @Override
    public void createQuiz(String name) throws IllegalArgumentException {
        if (inValid(name)) throw new IllegalArgumentException(ControllerMessages.EMPTY_TITLE);
        quizId = quizMaker.createQuiz(name);
    }

    @Override
    public void addQuestion(String question, int score) throws IllegalArgumentException, IllegalQuizCreationException {
        if (quizMaker.empty()) throw new IllegalQuizCreationException();
        if (score < 1) throw new IllegalArgumentException(ControllerMessages.INVALID_SCORE);
        if (inValid(question)) throw new IllegalArgumentException(ControllerMessages.EMPTY_QUESTION);
        quizMaker.addQuestion(question, score);
    }

    @Override
    public void addAnswer(String answer, boolean value) throws IllegalArgumentException, IllegalQuizCreationException {
        if (quizMaker.empty()) throw new IllegalQuizCreationException();
        if (inValid(answer)) throw new IllegalArgumentException(ControllerMessages.EMPTY_ANSWER);
        quizMaker.addAnswer(answer, value);
    }

    @Override
    public void save() throws IllegalQuizCreationException, InvalidQuizException {
        if (quizMaker.empty()) throw new IllegalQuizCreationException();
        if (!quizMaker.validQuiz()) throw new InvalidQuizException();
        quizMaker.save();
    }

    @Override
    public boolean validQuiz() {
        return quizMaker.validQuiz();
    }

    @Override
    public String getQuestion() {
        return quizMaker.getQuestion();
    }

    @Override
    public int getQuizId() {
        return quizId;
    }

    private boolean inValid(String argument) {
        return argument == null || argument.trim().isEmpty();
    }
}