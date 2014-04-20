package pij.ryan.durling.factories;

import pij.ryan.durling.models.Score;

import java.rmi.RemoteException;

public interface ScoreFactory {

    /**
     * Holds the name of the player and score
     *
     * @param playerName String
     * @param score int
     * @return Score
     * @throws RemoteException if problem with server
     */
    Score createScore(String playerName, int score) throws RemoteException;
}
