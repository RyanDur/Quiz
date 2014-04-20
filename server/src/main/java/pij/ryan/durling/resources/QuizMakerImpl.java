package pij.ryan.durling.resources;

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

    public QuizMakerImpl(QuizCtrl quizCtrl, QuizFactory quizFactory) throws RemoteException {
        this.quizCtrl = quizCtrl;
        this.quizFactory = quizFactory;
    }

    @Override
    public int createQuiz(String title) throws RemoteException {
        quiz = quizFactory.createQuiz(title);
        return quiz.getId();
    }

    @Override
    public void addQuestion(String questionString, int score) throws RemoteException {
        question = quizFactory.createQuestion(questionString, score);
        quiz.add(question);
    }

    @Override
    public void addAnswer(String answer, boolean value) throws RemoteException {
        question.add(quizFactory.createAnswer(answer, value));
    }

    @Override
    public void save() throws RemoteException {
        quizCtrl.add(quiz);
    }

    @Override
    public boolean validQuiz() throws RemoteException {
        return quiz.valid();
    }

    @Override
    public boolean empty() throws  RemoteException{
        return quiz == null;
    }

    @Override
    public boolean validQuestion() throws RemoteException {
        return question.valid();
    }

    @Override
    public Set<QuizOption> getAvailableQuizzes() throws RemoteException {
        return quizCtrl.getAvailableQuizzes();
    }

    @Override
    public Set<QuizOption> getClosedQuizzes() throws RemoteException {
        return quizCtrl.getClosedQuizzes();
    }

    @Override
    public void closeQuiz(int quizId) throws RemoteException {
        quizCtrl.close(quizId);
    }

    @Override
    public void openQuiz(int quizId) throws RemoteException {
        quizCtrl.open(quizId);
    }
}
