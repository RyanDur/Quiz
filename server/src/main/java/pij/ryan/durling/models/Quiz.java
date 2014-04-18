package pij.ryan.durling.models;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface Quiz extends Remote {
    String getName() throws RemoteException;

    int getId() throws RemoteException;

    boolean valid() throws RemoteException;

    void add(Question question) throws RemoteException;

    Set<Question> getQuestions() throws RemoteException;
}
