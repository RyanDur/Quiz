package pij.ryan.durling.servers;

import pij.ryan.durling.registry.Question;
import pij.ryan.durling.registry.Quiz;

public interface QuizServer {
    Quiz createQuiz(String name);

    Question createQuestion(String s);

    void save(Quiz quiz);
}
