package pij.ryan.durling.models;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ScoreImpl extends UnicastRemoteObject implements Score {
    private String playerName;
    private int score;

    public ScoreImpl(String playerName, int score) throws RemoteException {
        this.playerName = playerName;
        this.score = score;
    }

    @Override
    public int getScore() throws RemoteException {
        return score;
    }
}