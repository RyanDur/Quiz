package pij.ryan.durling.resources;

import pij.ryan.durling.controllers.QuizCtrl;
import pij.ryan.durling.factories.QuizFactory;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
    public String getName() throws RemoteException {
        return quiz.getName();
    }

    @Override
    public boolean empty() throws  RemoteException{
        return quiz == null;
    }

    @Override
    public int getId() throws RemoteException {
        return quiz.getId();
    }

    @Override
    public String getQuestion() throws RemoteException {
        return question.getQuestion();
    }

    @Override
    public int getQuestionValue() throws RemoteException {
        return question.getValue();
    }

    @Override
    public boolean validQuestion() throws RemoteException {
        return question.valid();
    }
}
