package pij.ryan.durling.factories;

import pij.ryan.durling.models.Score;

import java.rmi.RemoteException;

public interface ScoreFactory {
    Score createScore(String playerName, int score) throws RemoteException;
}
