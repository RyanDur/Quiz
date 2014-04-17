package pij.ryan.durling.controllers;

import com.google.inject.Inject;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;
import pij.ryan.durling.messages.ControllerMessages;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.resources.Server;
import pij.ryan.durling.resources.ServerLink;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class QuizCreatorImpl implements QuizCreator {

    private Server server;
    private Quiz quiz;
    private Question question;

    @Inject
    public QuizCreatorImpl(ServerLink serverLink) {
        try {
            this.server = serverLink.getServer();
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return quiz.getName();
    }

    @Override
    public void createQuiz(String name) throws IllegalArgumentException {
        if (inValid(name)) throw new IllegalArgumentException(ControllerMessages.EMPTY_TITLE);
        quiz = server.createQuiz(name);
    }

    @Override
    public void addQuestion(String questionString, int score) throws IllegalArgumentException, IllegalQuizCreationException {
        if (quiz == null) throw new IllegalQuizCreationException();
        if (score < 1) throw new IllegalArgumentException(ControllerMessages.INVALID_SCORE);
        if (inValid(questionString)) throw new IllegalArgumentException(ControllerMessages.EMPTY_QUESTION);
        question = server.createQuestion(questionString, score);
        quiz.add(question);
    }

    @Override
    public void addAnswer(String answerString, boolean value) throws IllegalArgumentException, IllegalQuizCreationException {
        if (quiz == null) throw new IllegalQuizCreationException();
        if (inValid(answerString)) throw new IllegalArgumentException(ControllerMessages.EMPTY_ANSWER);
        Answer answer = server.createAnswer(answerString, value);
        question.add(answer);
    }

    @Override
    public void save() throws IllegalQuizCreationException, InvalidQuizException {
        if (quiz == null) throw new IllegalQuizCreationException();
        if (!quiz.valid()) throw new InvalidQuizException();
        server.save(quiz);
    }

    @Override
    public boolean validQuiz() {
        return quiz.valid();
    }

    @Override
    public String getQuestion() {
        return question.getQuestion();
    }

    @Override
    public void lockQuiz(int id) {
        server.lock(id);
    }

    @Override
    public int getQuizId() {
        return quiz.getId();
    }

    @Override
    public void unlockQuiz(int id) {
        server.unlock(id);
    }

    private boolean inValid(String argument) {
        return argument == null || argument.trim().isEmpty();
    }
}