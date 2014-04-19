package pij.ryan.durling.resources;

import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.models.Score;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface QuizPlay extends Remote {
    Set<QuizOption> getQuizOptions() throws RemoteException;

    Quiz getQuiz(int id) throws RemoteException;

    Score getHighScore(int quizId) throws RemoteException;

    void setHighScore(int quizId, String playerName, int score) throws RemoteException;
}
