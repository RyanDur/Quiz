package pij.ryan.durling.resources;

import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOptions;

import java.util.Set;

public interface QuizServer {
    Set<QuizOptions> getQuizOptions();

    Quiz getQuiz(int quizId);

    boolean checkHighScore(Quiz quiz, String userName);
}
