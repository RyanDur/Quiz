package pij.ryan.durling.Client;

import pij.ryan.durling.registry.Question;
import pij.ryan.durling.registry.Quiz;

public interface QuizClient {
    Quiz create(String name);

    Question createQuestion(String s);
}
