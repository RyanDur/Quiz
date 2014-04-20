package pij.ryan.durling.models;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface QuizOption extends Remote {

    /**
     * return the title for a quiz
     *
     * @return string
     * @throws RemoteException
     */
    String getQuizTitle() throws RemoteException;

    /**
     * return the id for a quiz
     *
     * @return int
     * @throws RemoteException
     */
    int getQuizId() throws RemoteException;
}
