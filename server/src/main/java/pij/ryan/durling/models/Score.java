package pij.ryan.durling.models;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Score extends Remote {

    /**
     * get the score for a quiz
     *
     * @return int
     * @throws RemoteException if problem with server
     */
    int getScore() throws RemoteException;

    /**
     * get the name of a quiz
     *
     * @return string
     * @throws RemoteException if problem with server
     */
    String getName() throws RemoteException;
}
