package pij.ryan.durling.controllers;

import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.resources.QuizServer;

import java.util.List;

public class QuizPlayerImpl implements QuizPlayer {
    private QuizServer quizServer;
    private List<Quiz> quizList;

    public QuizPlayerImpl(QuizServer quizServer) {
        this.quizServer = quizServer;
    }

    @Override
    public List<Quiz> getQuizList() {
        quizList = quizServer.getQuizList();
        return quizList;
    }

    @Override
    public Quiz getQuiz(int quizIndex) {
        quizIndex -= 1;
        return quizList.get(quizIndex);
    }
}
