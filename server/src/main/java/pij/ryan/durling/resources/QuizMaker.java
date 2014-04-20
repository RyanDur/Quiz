package pij.ryan.durling.resources;

import pij.ryan.durling.models.QuizOption;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface QuizMaker extends Remote{

    /**
     * create a quiz
     *
     * @param title String
     * @return Quiz
     * @throws RemoteException if problem with server
     */
    int createQuiz(String title) throws RemoteException;

    /**
     * Add a question to a quiz
     *
     * @param question String
     * @param score int
     * @throws RemoteException if problem with server
     */
    void addQuestion(String question, int score) throws RemoteException;

    /**
     * Add an answer to a quiz
     *
     * @param answer String
     * @param value boolean
     * @throws RemoteException if problem with server
     */
    void addAnswer(String answer, boolean value) throws RemoteException;

    /**
     * Save a quiz
     *
     * @throws RemoteException if problem with server
     */
    void save() throws RemoteException;

    /**
     * Check if quiz is valid
     *
     * @return boolean
     * @throws RemoteException if problem with server
     */
    boolean validQuiz() throws RemoteException;

    /**
     * check if there is no quiz
     *
     * @return boolean
     * @throws RemoteException if problem with server
     */
    boolean empty() throws RemoteException;

    /**
     * check if the question is valid
     *
     * @return boolean
     * @throws RemoteException if problem with server
     */
    boolean validQuestion() throws RemoteException;

    /**
     * get a list of available quizzes
     *
     * @return Set of quiz options
     * @throws RemoteException if problem with server
     */
    Set<QuizOption> getAvailableQuizzes() throws RemoteException;

    /**
     * get a list of closed quizzes
     *
     * @return Set of quiz options
     * @throws RemoteException if problem with server
     */
    Set<QuizOption> getClosedQuizzes() throws RemoteException;

    /**
     * close a quiz
     *
     * @param quizId int
     * @throws RemoteException if problem with server
     */
    void closeQuiz(int quizId) throws RemoteException;

    /**
     * open a quiz
     *
     * @param quizId int
     * @throws RemoteException if problem with server
     */
    void openQuiz(int quizId) throws RemoteException;
}
