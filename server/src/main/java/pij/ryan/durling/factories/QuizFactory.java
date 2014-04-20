package pij.ryan.durling.factories;

import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;

import java.rmi.RemoteException;

public interface QuizFactory {

    /**
     * create a new quiz
     *
     * @param title of the quiz
     * @return a new quiz
     * @throws RemoteException
     */
    Quiz createQuiz(String title) throws RemoteException;

    /**
     * Create a new question
     *
     * @param question the users question
     * @param score score for the question
     * @return a new question
     * @throws RemoteException
     */
    Question createQuestion(String question, int score) throws RemoteException;

    /**
     * create a new answer
     *
     * @param answer users answer
     * @param value iif the answer is correct
     * @return a new answer
     * @throws RemoteException
     */
    Answer createAnswer(String answer, boolean value) throws RemoteException;
}
