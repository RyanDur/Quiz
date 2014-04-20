package pij.ryan.durling.resources;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface ServerLink {

    /**
     * Get the quiz maker from the server
     * @return QuizMaker
     * @throws RemoteException
     * @throws NotBoundException
     */
    QuizMaker getQuizMaker() throws RemoteException, NotBoundException;
}
