package pij.ryan.durling.resources;

import java.rmi.RemoteException;

public interface Server {
    QuizPlay getQuizPlay() throws RemoteException;
}
