package pij.ryan.durling.controllers;

import pij.ryan.durling.client.QuizClient;
import pij.ryan.durling.registry.Question;
import pij.ryan.durling.registry.Quiz;

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
    public int create(String name) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException();
        Quiz quiz = quizClient.create(name);
        quizMap.put(quiz.getId(), quiz);
        return quiz.getId();
    }

    @Override
    public Quiz get(int quizId) {
        return quizMap.get(quizId);
    }

    @Override
    public void addQuestion(String question, int quizId) throws IllegalArgumentException {
        if (question == null || question.trim().isEmpty()) throw new IllegalArgumentException();
        Quiz quiz = quizMap.get(quizId);
        Question question1 = quizClient.createQuestion(question);
        quiz.addQuestion(question1);
    }
}