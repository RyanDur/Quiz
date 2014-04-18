package pij.ryan.durling.factories;

import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.models.QuizOptionImpl;

import java.rmi.RemoteException;

public class OptionFactoryImpl implements OptionFactory {
    @Override
    public QuizOption createQuizOption(int id, String name) throws RemoteException {
        return new QuizOptionImpl(name, id);
    }
}
