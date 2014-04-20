package pij.ryan.durling.controllers;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pij.ryan.durling.messages.ControllerMessages;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.models.Score;
import pij.ryan.durling.resources.QuizMaster;
import pij.ryan.durling.resources.ServerLink;
import pij.ryan.durling.resources.ServerLinkImpl;

import java.util.Set;

public class QuizPlayerImpl implements QuizPlayer {
    private static final Logger log = LoggerFactory.getLogger(ServerLinkImpl.class);
    private QuizMaster quizMaster;
    private Quiz quiz;
    private String playerName;
    private int score;
    private String oldCurrentWinner;
    private int oldHighScore;


    @Inject
    public QuizPlayerImpl(ServerLink serverLink) {
        this.quizMaster = serverLink.getQuizMaster();
    }

    @Override
    public Set<QuizOption> getMenu() {
        return quizMaster.getQuizOptions();
    }

    @Override
    public void chooseQuiz(int choice) {
        score = 0;
        quiz = quizMaster.getQuiz(choice);
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
    public String getOldCurrentWinner() {
        return oldCurrentWinner;
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
        getHighScore();
        setHighScore();
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
    public int getOldHighScore() {
        return oldHighScore;
    }

    @Override
    public boolean hasWon() {
        return getScore() >= getOldHighScore();
    }

    private void setHighScore() {
        if (getOldHighScore() == 0 || hasWon()) {
            quizMaster.setHighScore(quiz.getId(), getPlayerName(), getScore());
        }
    }

    private void getHighScore() {
        Score scoreObj = quizMaster.getHighScore(quiz.getId());
        if (scoreObj != null) {
            oldCurrentWinner = scoreObj.getName();
            oldHighScore = scoreObj.getScore();
        }
    }
}
