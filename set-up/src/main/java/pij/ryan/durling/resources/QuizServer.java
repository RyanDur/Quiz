package pij.ryan.durling.resources;

import pij.ryan.durling.registry.Answer;
import pij.ryan.durling.registry.Question;
import pij.ryan.durling.registry.Quiz;

public interface QuizServer {
    Quiz createQuiz(String name);

    Question createQuestion(String s, int score);

    void save(Quiz quiz);

    Answer createAnswer(String answer, boolean value);
}
