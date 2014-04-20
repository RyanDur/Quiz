package pij.ryan.durling.models;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface Quiz extends Remote {

    /**
     * the name of the quiz
     *
     * @return a string
     * @throws RemoteException
     */
    String getName() throws RemoteException;

    /**
     * the id for the quiz
     *
     * @return int
     * @throws RemoteException
     */
    int getId() throws RemoteException;

    /**
     * check if the quiz is valid, i.e. if the quiz has one or more questions
     *
     * @return true or false
     * @throws RemoteException
     */
    boolean valid() throws RemoteException;

    /**
     * Add a question to the quiz
     *
     * @param question question for the quiz
     * @throws RemoteException
     */
    void add(Question question) throws RemoteException;

    /**
     * gets a set of questions for the quiz
     *
     * @return a set of questions
     * @throws RemoteException
     */
    Set<Question> getQuestions() throws RemoteException;
}
