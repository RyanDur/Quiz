package pij.ryan.durling.models;

import pij.ryan.durling.models.Player;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.resources.QuizServer;

import java.util.List;

public class PlayerImpl implements Player {

    private QuizServer quizServer;
    private List<Quiz> quizList;

    public PlayerImpl(QuizServer quizServer) {
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
