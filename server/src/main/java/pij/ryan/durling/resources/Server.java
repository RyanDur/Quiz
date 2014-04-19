package pij.ryan.durling.resources;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {

    QuizMaker getQuizMaker() throws RemoteException;

    QuizPlay getQuizPlay() throws RemoteException;
}
