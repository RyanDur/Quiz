package pij.ryan.durling.factories;

import pij.ryan.durling.models.QuizOption;

import java.rmi.RemoteException;

public interface OptionFactory {

    /**
     * Hold the name and id for a quiz
     *
     * @param id of the quiz
     * @param name name of the quiz
     * @return a quiz option
     * @throws RemoteException if problem with server
     */
    QuizOption createQuizOption(int id, String name) throws RemoteException;
}
