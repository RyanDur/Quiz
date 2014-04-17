package pij.ryan.durling.controllers;

import com.google.inject.Inject;
import pij.ryan.durling.messages.ControllerMessages;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.resources.QuizServer;

import java.util.Set;

public class QuizPlayerImpl implements QuizPlayer {
    private QuizServer quizServer;
    private Quiz quiz;
    private String playerName;
    private int score;
    private boolean winner;


    @Inject
    public QuizPlayerImpl(QuizServer quizServer) {
        this.quizServer = quizServer;
    }

    @Override
    public Set<QuizOption> getMenu() {
        return quizServer.getQuizOptions();
    }

    @Override
    public void chooseQuiz(int choice) {
        quiz = quizServer.getQuiz(choice);
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
        score = quiz.getScore();
        winner = quizServer.checkHighScore(quiz, getPlayerName());
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public boolean hasWon() {
        return winner;
    }
}
