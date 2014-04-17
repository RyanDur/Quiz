package pij.ryan.durling.models;

import java.rmi.RemoteException;

public interface Answer {
    String getAnswer() throws RemoteException;

    boolean getValue() throws RemoteException;
}
