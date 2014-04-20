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
     * @throws RemoteException
     */
    int createQuiz(String title) throws RemoteException;

    /**
     * Add a question to a quiz
     *
     * @param question String
     * @param score int
     * @throws RemoteException
     */
    void addQuestion(String question, int score) throws RemoteException;

    /**
     * Add an answer to a quiz
     *
     * @param answer String
     * @param value boolean
     * @throws RemoteException
     */
    void addAnswer(String answer, boolean value) throws RemoteException;

    /**
     * Save a quiz
     *
     * @throws RemoteException
     */
    void save() throws RemoteException;

    /**
     * Check if quiz is valid
     *
     * @return boolean
     * @throws RemoteException
     */
    boolean validQuiz() throws RemoteException;

    /**
     * check if there is no quiz
     *
     * @return boolean
     * @throws RemoteException
     */
    boolean empty() throws RemoteException;

    /**
     * check if the question is valid
     *
     * @return boolean
     * @throws RemoteException
     */
    boolean validQuestion() throws RemoteException;

    /**
     * get a list of available quizzes
     *
     * @return Set of quiz options
     * @throws RemoteException
     */
    Set<QuizOption> getAvailableQuizzes() throws RemoteException;

    /**
     * get a list of closed quizzes
     *
     * @return Set of quiz options
     * @throws RemoteException
     */
    Set<QuizOption> getClosedQuizzes() throws RemoteException;

    /**
     * close a quiz
     *
     * @param quizId int
     * @throws RemoteException
     */
    void closeQuiz(int quizId) throws RemoteException;

    /**
     * open a quiz
     *
     * @param quizId int
     * @throws RemoteException
     */
    void openQuiz(int quizId) throws RemoteException;
}
