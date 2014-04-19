package pij.ryan.durling.models;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Answer extends Remote {
    String getAnswer() throws RemoteException;

    boolean getValue() throws RemoteException;
}
