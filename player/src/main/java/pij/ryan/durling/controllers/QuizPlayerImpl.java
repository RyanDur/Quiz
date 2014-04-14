package pij.ryan.durling.controllers;

import pij.ryan.durling.messages.ControllerMessages;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.resources.QuizServer;

import java.util.Set;

public class QuizPlayerImpl implements QuizPlayer {
    private QuizServer quizServer;
    private QuizElements quizElements;
    private Menu menu;
    private Quiz quiz;
    private String playerName;
    private int score;
    private boolean winner;


    public QuizPlayerImpl(QuizServer quizServer, QuizElements quizElements) {
        this.quizServer = quizServer;
        this.quizElements = quizElements;
    }

    @Override
    public Menu getMenu() {
        menu = quizElements.getMenu(quizServer.getQuizOptions());
        return menu;
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
