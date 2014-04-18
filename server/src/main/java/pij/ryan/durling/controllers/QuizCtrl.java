package pij.ryan.durling.controllers;

import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;

import java.rmi.RemoteException;
import java.util.Set;

public interface QuizCtrl {
    void add(Quiz quiz) throws RemoteException;

    Set<QuizOption> getQuizOptions();

    Quiz getQuiz(int id);

    void flush();
}
