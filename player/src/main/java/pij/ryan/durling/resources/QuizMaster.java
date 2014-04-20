package pij.ryan.durling.resources;

import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.models.Score;

import java.util.Set;

public interface QuizMaster {
    Set<QuizOption> getQuizOptions();

    Quiz getQuiz(int id);

    Score getHighScore(int quizId);

    void setHighScore(int quizId, String playerName, int score);
}
