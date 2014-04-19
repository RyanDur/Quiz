package pij.ryan.durling.factories;

import com.google.inject.Singleton;
import pij.ryan.durling.models.Score;
import pij.ryan.durling.models.ScoreImpl;

import java.rmi.RemoteException;

@Singleton
public class ScoreFactoryImpl implements ScoreFactory {
    @Override
    public Score createScore(String playerName, int score) throws RemoteException {
        return new ScoreImpl(playerName, score);
    }
}
