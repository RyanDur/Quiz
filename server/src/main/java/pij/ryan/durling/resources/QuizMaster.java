package pij.ryan.durling.resources;

import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.models.Score;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface QuizMaster extends Remote {

    /**
     * Get a list of quizzes
     *
     * @return a set of quiz options
     * @throws RemoteException if problem with server
     */
    Set<QuizOption> getQuizOptions() throws RemoteException;

    /**
     * Get a quiz
     *
     * @param id int
     * @return Quiz
     * @throws RemoteException if problem with server
     */
    Quiz getQuiz(int id) throws RemoteException;

    /**
     * Get the high score information for a quiz
     *
     * @param quizId int
     * @return Score
     * @throws RemoteException if problem with server
     */
    Score getHighScore(int quizId) throws RemoteException;

    /**
     * Set the high score for a quiz
     *
     * @param quizId int
     * @param playerName String
     * @param score int
     * @throws RemoteException if problem with server
     */
    void setHighScore(int quizId, String playerName, int score) throws RemoteException;
}
