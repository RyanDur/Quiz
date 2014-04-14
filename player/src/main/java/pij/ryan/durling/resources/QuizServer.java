package pij.ryan.durling.resources;

import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;

import java.util.Set;

public interface QuizServer {
    Set<QuizOption> getQuizOptions();

    Quiz getQuiz(int quizId);

    boolean checkHighScore(Quiz quiz, String userName);
}
