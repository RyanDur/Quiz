package pij.ryan.durling.models;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface QuizOption extends Remote {
    String getQuizTitle() throws RemoteException;

    int getQuizId() throws RemoteException;
}
