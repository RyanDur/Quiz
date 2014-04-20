package pij.ryan.durling.resources;

import pij.ryan.durling.models.QuizOption;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface QuizMaker extends Remote{
    int createQuiz(String title) throws RemoteException;

    void addQuestion(String question, int score) throws RemoteException;

    void addAnswer(String answer, boolean value) throws RemoteException;

    void save() throws RemoteException;

    boolean validQuiz() throws RemoteException;

    boolean empty() throws RemoteException;

    boolean validQuestion() throws RemoteException;

    Set<QuizOption> getAvailableQuizzes() throws RemoteException;

    Set<QuizOption> getClosedQuizzes() throws RemoteException;

    void closeQuiz(int quizId) throws RemoteException;

    void openQuiz(int quizId) throws RemoteException;
}
