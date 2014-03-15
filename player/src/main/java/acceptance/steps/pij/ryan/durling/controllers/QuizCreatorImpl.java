package acceptance.steps.pij.ryan.durling.controllers;

import acceptance.steps.pij.ryan.durling.factories.QuizClient;
import acceptance.steps.pij.ryan.durling.registry.Quiz;

import java.util.HashMap;
import java.util.Map;

public class QuizCreatorImpl implements QuizCreator {

    private final QuizClient quizClient;
    private final Map<Integer,Quiz> quizMap;

    public QuizCreatorImpl(QuizClient quizClient) {
        this.quizClient = quizClient;
        quizMap = new HashMap<>();
    }

    @Override
    public int create(String name) {
        Quiz quiz = quizClient.create(name);
        quizMap.put(quiz.getId(), quiz);
        return quiz.getId();
    }

    @Override
    public Quiz get(int quizId) {
        return quizMap.get(quizId);
    }
}