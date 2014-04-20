package pij.ryan.durling.resources;

import java.rmi.RemoteException;

public interface Server {
    QuizMaster getQuizPlay() throws RemoteException;
}
