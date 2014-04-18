package pij.ryan.durling.controllers;

import pij.ryan.durling.models.Quiz;

public interface HighScoreCtrl {
    boolean checkHighScore(Quiz quiz, int score);

    void setHighScore(Quiz quiz, String player, int score);
}
