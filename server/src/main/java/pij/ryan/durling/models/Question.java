package pij.ryan.durling.models;

import java.rmi.RemoteException;
import java.util.Set;

public interface Question {
    String getQuestion() throws RemoteException;

    int getValue() throws RemoteException;

    void add(Answer answer) throws RemoteException;

    boolean valid() throws RemoteException;

    Set<Answer> getAnswers() throws RemoteException;
}
