package pij.ryan.durling.resources;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface QuizMaker extends Remote{
    int createQuiz(String title) throws RemoteException;

    void addQuestion(String question, int score) throws RemoteException;

    void addAnswer(String answer, boolean value) throws RemoteException;

    void save() throws RemoteException;

    boolean validQuiz() throws RemoteException;

    String getName() throws RemoteException;

    boolean empty() throws RemoteException;

    int getId() throws RemoteException;

    String getQuestion() throws RemoteException;

    int getQuestionValue() throws RemoteException;

    boolean validQuestion() throws RemoteException;
}
