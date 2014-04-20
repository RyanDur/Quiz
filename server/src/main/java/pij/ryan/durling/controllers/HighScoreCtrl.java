package pij.ryan.durling.controllers;

import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.Score;

import java.rmi.RemoteException;
import java.util.TreeMap;

public interface HighScoreCtrl {
    void setHighScore(int quizId, String player, int score) throws RemoteException;

    Score getHighScore(int quizId) throws RemoteException;

    TreeMap<Integer, Score> getScores();
}
