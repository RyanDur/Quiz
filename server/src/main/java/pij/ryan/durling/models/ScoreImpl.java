package pij.ryan.durling.models;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ScoreImpl extends UnicastRemoteObject implements Score {
    public ScoreImpl(String playerName, int score) throws RemoteException {
    }

    @Override
    public int getScore() throws RemoteException {
        return 0;
    }
}
