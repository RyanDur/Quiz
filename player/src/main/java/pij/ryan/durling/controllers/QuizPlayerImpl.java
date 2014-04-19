package pij.ryan.durling.controllers;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pij.ryan.durling.messages.ControllerMessages;
import pij.ryan.durling.messages.ServerMessages;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.models.Score;
import pij.ryan.durling.resources.QuizPlay;
import pij.ryan.durling.resources.ServerLink;
import pij.ryan.durling.resources.ServerLinkImpl;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Set;

public class QuizPlayerImpl implements QuizPlayer {
    private static final Logger log = LoggerFactory.getLogger(ServerLinkImpl.class);
    private QuizPlay quizPlay;
    private Quiz quiz;
    private String playerName;
    private int score;
    private boolean winner;
    private Score scoreObj;
    private int oldHighScore;


    @Inject
    public QuizPlayerImpl(ServerLink serverLink) {
        try {
            this.quizPlay = serverLink.getQuizPlay();
        } catch (RemoteException | NotBoundException e) {
            log.error(ServerMessages.ERROR_MESSAGE, e);
        }
    }

    @Override
    public Set<QuizOption> getMenu() {
        return quizPlay.getQuizOptions();
    }

    @Override
    public void chooseQuiz(int choice) {
        score = 0;
        quiz = quizPlay.getQuiz(choice);
    }

    @Override
    public Set<Question> getQuestions() {
        return quiz.getQuestions();
    }

    @Override
    public String getQuizName() {
        return quiz.getName();
    }

    @Override
    public void setPlayerName(String name) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException(ControllerMessages.EMPTY_NAME);
        playerName = name;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public void submitQuiz() {
        scoreObj = quizPlay.getHighScore(quiz.getId());
        oldHighScore = scoreObj.getScore();
        setWinner(getOldHighScore() < getScore());
        if (hasWon()) quizPlay.setHighScore(quiz.getId(), getPlayerName(), getScore());
    }

    @Override
    public void addToScore(int score) {
        this.score += score;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public boolean hasWon() {
        return winner;
    }

    @Override
    public String getOldCurrentWinner() {
        return scoreObj.getName();
    }

    @Override
    public int getOldHighScore() {
        return oldHighScore;
    }

    private void setWinner(boolean winner) {
        this.winner = winner;
    }
}
