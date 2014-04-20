package pij.ryan.durling.resources;

import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.models.Score;

import java.util.Set;

public interface QuizMaster {
    /**
     * Get a list of quizzes
     *
     * @return a set of quiz options
     */
    Set<QuizOption> getQuizOptions();

    /**
     * Get a quiz
     *
     * @param id int
     * @return Quiz
     */
    Quiz getQuiz(int id);

    /**
     * Get the high score information for a quiz
     *
     * @param quizId int
     * @return Score
     */
    Score getHighScore(int quizId);

    /**
     * Set the high score for a quiz
     *
     * @param quizId int
     * @param playerName String
     * @param score int
     */
    void setHighScore(int quizId, String playerName, int score);
}
