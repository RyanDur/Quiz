package pij.ryan.durling.factories;

import pij.ryan.durling.models.Score;
import pij.ryan.durling.models.ScoreImpl;

import java.rmi.RemoteException;

public class ScoreFactoryImpl implements ScoreFactory {
    @Override
    public Score createScore(String playerName, int score) throws RemoteException {
        return new ScoreImpl(playerName, score);
    }
}
