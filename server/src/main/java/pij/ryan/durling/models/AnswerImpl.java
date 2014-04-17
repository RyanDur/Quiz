package pij.ryan.durling.models;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AnswerImpl extends UnicastRemoteObject implements Answer {

    private String answer;
    private boolean correct;

    public AnswerImpl(String answer, boolean correct) throws RemoteException {
        this.answer = answer;
        this.correct = correct;
    }

    @Override
    public String getAnswer() throws RemoteException {
        return answer;
    }

    @Override
    public boolean getValue() throws RemoteException {
        return correct;
    }
}
