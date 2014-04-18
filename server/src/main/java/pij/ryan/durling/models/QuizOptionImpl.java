package pij.ryan.durling.models;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class QuizOptionImpl extends UnicastRemoteObject implements QuizOption {
    private String title;
    private int id;

    public QuizOptionImpl(String title, int id) throws RemoteException {
        this.title = title;
        this.id = id;
    }

    @Override
    public String getQuizTitle() throws RemoteException {
        return title;
    }

    @Override
    public int getQuizId() throws RemoteException {
        return id;
    }
}
