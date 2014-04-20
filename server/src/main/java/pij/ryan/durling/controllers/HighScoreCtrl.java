package pij.ryan.durling.controllers;

import pij.ryan.durling.models.Score;

import java.rmi.RemoteException;

public interface HighScoreCtrl {
    /**
     * Set the high score for a given quiz
     *
     * @param quizId id of the quiz
     * @param player name of the player
     * @param score score of the player for the quiz
     * @throws RemoteException
     */
    void setHighScore(int quizId, String player, int score) throws RemoteException;

    /**
     * Get the high score for a quiz
     *
     * @param quizId the id of th quiz
     * @return a score object
     * @throws RemoteException
     */
    Score getHighScore(int quizId) throws RemoteException;
}
