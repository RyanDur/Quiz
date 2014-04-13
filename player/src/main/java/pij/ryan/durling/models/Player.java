package pij.ryan.durling.models;

import pij.ryan.durling.models.Quiz;

import java.util.List;

public interface Player {
    List<Quiz> getQuizList();

    Quiz getQuiz(int quizIndex);
}
