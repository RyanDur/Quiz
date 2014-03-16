package pij.ryan.durling.Client;

import pij.ryan.durling.registry.Question;
import pij.ryan.durling.registry.Quiz;

public interface QuizClient {
    Quiz create(String name);

    Quiz get(int i);

    Question createQuestion(String s);
}
