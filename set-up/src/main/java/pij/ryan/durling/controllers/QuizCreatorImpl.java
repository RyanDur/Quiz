package pij.ryan.durling.controllers;

import com.google.inject.Inject;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;
import pij.ryan.durling.messages.ControllerMessages;
import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.resources.QuizMaker;
import pij.ryan.durling.resources.ServerLink;

import java.util.Set;

public class QuizCreatorImpl implements QuizCreator {

    private QuizMaker quizMaker;
    private String name;
    private String question;

    @Inject
    public QuizCreatorImpl(ServerLink serverLink) {
        quizMaker = serverLink.getQuizMaker();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void createQuiz(String name) throws IllegalArgumentException {
        if (inValid(name)) throw new IllegalArgumentException(ControllerMessages.EMPTY_TITLE);
        this.name = name;
        quizMaker.createQuiz(name);
    }

    @Override
    public void addQuestion(String question, int score) throws IllegalArgumentException, IllegalQuizCreationException {
        if (quizMaker.empty()) throw new IllegalQuizCreationException();
        if (score < 1) throw new IllegalArgumentException(ControllerMessages.INVALID_SCORE);
        if (inValid(question)) throw new IllegalArgumentException(ControllerMessages.EMPTY_QUESTION);
        this.question = question;
        quizMaker.addQuestion(question, score);
    }

    @Override
    public void addAnswer(String answer, boolean value) throws IllegalArgumentException, IllegalQuizCreationException {
        if (quizMaker.empty()) throw new IllegalQuizCreationException();
        if (inValid(answer)) throw new IllegalArgumentException(ControllerMessages.EMPTY_ANSWER);
        quizMaker.addAnswer(answer, value);
    }

    @Override
    public void save() throws IllegalQuizCreationException, InvalidQuizException {
        if (quizMaker.empty()) throw new IllegalQuizCreationException();
        if (!quizMaker.validQuiz()) throw new InvalidQuizException();
        quizMaker.save();
    }

    @Override
    public boolean validQuiz() {
        return quizMaker.validQuiz();
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public Set<QuizOption> getAvailableQuizzes() {
        return quizMaker.getAvailableQuizzes();
    }

    @Override
    public Set<QuizOption> getClosedQuizzes() {
        return quizMaker.getClosedQuizzes();
    }

    @Override
    public void closeQuiz(int quizId) {
        quizMaker.closeQuiz(quizId);
    }

    @Override
    public void openQuiz(int quizId) {
        quizMaker.openQuiz(quizId);
    }

    private boolean inValid(String argument) {
        return argument == null || argument.trim().isEmpty();
    }
}