package pij.ryan.durling.client;

import pij.ryan.durling.registry.Question;
import pij.ryan.durling.registry.Quiz;

public interface QuizClient {
    Quiz create(String name);

    Question createQuestion(String s);
}
