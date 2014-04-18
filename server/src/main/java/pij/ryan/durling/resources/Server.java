package pij.ryan.durling.resources;

import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;

import java.rmi.RemoteException;
import java.util.Set;

public interface Server {
    Quiz createQuiz(String title);

    Question createQuestion(String question, int score) throws RemoteException;

    Answer createAnswer(String answer, boolean value) throws RemoteException;

    void save(Quiz quiz);

    Set<QuizOption> getQuizOptions();

    Quiz getQuiz(int id);

    boolean checkHighScore(Quiz quiz, int score);

    void setHighScore(Quiz quiz, String playerName, int score);
}
