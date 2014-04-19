package pij.ryan.durling.models;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Score extends Remote {
    int getScore() throws RemoteException;

    String getName() throws RemoteException;
}
