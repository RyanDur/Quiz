package pij.ryan.durling.resources;

import com.google.inject.Inject;
import pij.ryan.durling.controllers.HighScoreCtrl;
import pij.ryan.durling.controllers.QuizCtrl;
import pij.ryan.durling.factories.QuizFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements Server {
    private QuizCtrl quizCtrl;
    private HighScoreCtrl highSoreCtrl;
    private QuizFactory quizFactory;

    @Inject
    public ServerImpl(QuizCtrl quizCtrl, HighScoreCtrl highSoreCtrl, QuizFactory quizFactory) throws RemoteException {
        this.quizCtrl = quizCtrl;
        this.highSoreCtrl = highSoreCtrl;
        this.quizFactory = quizFactory;
    }

    @Override
    public QuizMaker getQuizMaker() throws RemoteException {
        return new QuizMakerImpl(quizCtrl, quizFactory);
    }

    @Override
    public QuizPlay getQuizPlay() throws RemoteException {
        return new QuizPlayImpl(quizCtrl, highSoreCtrl);
    }
}
