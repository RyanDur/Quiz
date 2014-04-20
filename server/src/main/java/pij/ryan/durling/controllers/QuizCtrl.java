package pij.ryan.durling.controllers;

import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;

import java.rmi.RemoteException;
import java.util.Set;

public interface QuizCtrl {

    /**
     * Add a quiz to the collection
     *
     * @param quiz to be added
     * @throws RemoteException
     */
    void add(Quiz quiz) throws RemoteException;

    /**
     * A list of available quizzes
     *
     * @return a set of qiz names and ids
     */
    Set<QuizOption> getAvailableQuizzes();

    /**
     * Get a specific quiz
     *
     * @param id the id of the quiz
     * @return a quiz
     */
    Quiz getQuiz(int id);

    /**
     * A list of closed quizzes
     *
     * @return a set of qiz names and ids
     */
    Set<QuizOption> getClosedQuizzes();

    /**
     * open a quiz
     *
     * @param quizId the quiz id
     */
    void open(int quizId);

    /**
     * Close a quiz
     *
     * @param quizId the quiz id
     */
    void close(int quizId);
}
