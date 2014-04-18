package pij.ryan.durling.factories;

import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;

import java.rmi.RemoteException;

public interface QuizFactory {
    Quiz createQuiz(String title);

    Question createQuestion(String question, int score) throws RemoteException;

    Answer createAnswer(String answer, boolean value) throws RemoteException;
}
