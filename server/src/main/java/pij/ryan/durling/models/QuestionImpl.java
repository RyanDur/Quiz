package pij.ryan.durling.models;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Set;

public class QuestionImpl extends UnicastRemoteObject implements Question {
    private String question;
    private int score;
    private final Set<Answer> answers;

    public QuestionImpl(String question, int score) throws RemoteException {
        this.question = question;
        this.score = score;
        answers = new HashSet<>();
    }

    @Override
    public String getQuestion() throws RemoteException {
        return question;
    }

    @Override
    public int getValue() throws RemoteException {
        return score;
    }

    @Override
    public void add(Answer answer) throws RemoteException {
        answers.add(answer);
    }

    @Override
    public boolean valid() throws RemoteException {
        return !answers.isEmpty();
    }

    @Override
    public Set<Answer> getAnswers() throws RemoteException {
        return answers;
    }
}
