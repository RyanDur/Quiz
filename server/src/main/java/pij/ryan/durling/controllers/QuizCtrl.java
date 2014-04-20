package pij.ryan.durling.controllers;

import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;

import java.rmi.RemoteException;
import java.util.Set;

public interface QuizCtrl {
    void add(Quiz quiz) throws RemoteException;

    Set<QuizOption> getAvailableQuizzes();

    Quiz getQuiz(int id);

    Set<QuizOption> getClosedQuizzes();

    void open(int quizId);

    void close(int quizId);
}
