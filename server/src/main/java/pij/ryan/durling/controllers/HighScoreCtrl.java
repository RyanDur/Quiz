package pij.ryan.durling.controllers;

import pij.ryan.durling.models.Quiz;

import java.rmi.RemoteException;

public interface HighScoreCtrl {
    boolean checkHighScore(Quiz quiz, int score) throws RemoteException;

    void setHighScore(Quiz quiz, String player, int score) throws RemoteException;
}
