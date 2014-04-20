package pij.ryan.durling.resources;

import com.google.inject.Inject;
import pij.ryan.durling.controllers.QuizCtrl;
import pij.ryan.durling.factories.QuizFactory;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

public class QuizMakerImpl extends UnicastRemoteObject implements QuizMaker {

    private QuizCtrl quizCtrl;
    private QuizFactory quizFactory;
    private Quiz quiz;
    private Question question;

    @Inject
    public QuizMakerImpl(QuizCtrl quizCtrl, QuizFactory quizFactory) throws RemoteException {
        this.quizCtrl = quizCtrl;
        this.quizFactory = quizFactory;
    }

    @Override
    public synchronized int createQuiz(String title) throws RemoteException {
        quiz = quizFactory.createQuiz(title);
        return quiz.getId();
    }

    @Override
    public synchronized void addQuestion(String questionString, int score) throws RemoteException {
        question = quizFactory.createQuestion(questionString, score);
        quiz.add(question);
    }

    @Override
    public synchronized void addAnswer(String answer, boolean value) throws RemoteException {
        question.add(quizFactory.createAnswer(answer, value));
    }

    @Override
    public synchronized void save() throws RemoteException {
        quizCtrl.add(quiz);
    }

    @Override
    public synchronized boolean validQuiz() throws RemoteException {
        return quiz.valid();
    }

    @Override
    public synchronized boolean empty() throws  RemoteException{
        return quiz == null;
    }

    @Override
    public synchronized boolean validQuestion() throws RemoteException {
        return question.valid();
    }

    @Override
    public synchronized Set<QuizOption> getAvailableQuizzes() throws RemoteException {
        return quizCtrl.getAvailableQuizzes();
    }

    @Override
    public synchronized Set<QuizOption> getClosedQuizzes() throws RemoteException {
        return quizCtrl.getClosedQuizzes();
    }

    @Override
    public synchronized void closeQuiz(int quizId) throws RemoteException {
        quizCtrl.close(quizId);
    }

    @Override
    public synchronized void openQuiz(int quizId) throws RemoteException {
        quizCtrl.open(quizId);
    }
}
