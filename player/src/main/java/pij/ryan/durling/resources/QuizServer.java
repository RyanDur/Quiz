package pij.ryan.durling.resources;

import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOptions;

import java.util.List;

public interface QuizServer {
    List<QuizOptions> getQuizOptions();

    Quiz getQuiz(int quizId);
}
