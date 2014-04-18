package pij.ryan.durling.factories;

import pij.ryan.durling.models.QuizOption;

import java.rmi.RemoteException;

public interface OptionFactory {
    QuizOption createQuizOption(int id, String name) throws RemoteException;
}
