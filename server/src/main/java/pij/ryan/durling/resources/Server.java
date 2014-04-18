package pij.ryan.durling.resources;

import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface Server extends Remote {
    Quiz createQuiz(String title) throws RemoteException;

    Question createQuestion(String question, int score) throws RemoteException;

    Answer createAnswer(String answer, boolean value) throws RemoteException;

    void save(Quiz quiz) throws RemoteException;

    Set<QuizOption> getQuizOptions() throws RemoteException;

    Quiz getQuiz(int id) throws RemoteException;

    boolean checkHighScore(Quiz quiz, int score) throws RemoteException;

    void setHighScore(Quiz quiz, String playerName, int score) throws RemoteException;
}
