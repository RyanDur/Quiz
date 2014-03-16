package pij.ryan.durling.client;

import pij.ryan.durling.registry.Question;
import pij.ryan.durling.registry.Quiz;

public interface QuizClient {
    Quiz createQuiz(String name);

    Question createQuestion(String s);

    void save(Quiz quiz);
}
