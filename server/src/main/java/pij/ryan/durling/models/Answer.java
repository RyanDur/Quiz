package pij.ryan.durling.models;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Answer extends Remote {

    /**
     * Get the answer string
     *
     * @return an answer string
     * @throws RemoteException if problem with server
     */
    String getAnswer() throws RemoteException;

    /**
     * tell if the answer is correct
     *
     * @return true or false
     * @throws RemoteException if problem with server
     */
    boolean getValue() throws RemoteException;
}
