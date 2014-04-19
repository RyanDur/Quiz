package pij.ryan.durling.models;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Set;

public class QuizImpl extends UnicastRemoteObject implements Quiz, Serializable {
    private String title;
    private int id;
    private final Set<Question> questions;

    public QuizImpl(String title, int id) throws RemoteException {
        this.title = title;
        this.id = id;
        questions = new HashSet<>();
    }

    @Override
    public String getName() throws RemoteException {
        return title;
    }

    @Override
    public int getId() throws RemoteException {
        return id;
    }

    @Override
    public boolean valid() throws RemoteException {
        return !questions.isEmpty();
    }

    @Override
    public void add(Question question) throws RemoteException {
        questions.add(question);
    }

    @Override
    public Set<Question> getQuestions() throws RemoteException {
        return questions;
    }
}
