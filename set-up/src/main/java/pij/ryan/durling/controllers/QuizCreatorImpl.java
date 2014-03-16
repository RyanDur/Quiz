package pij.ryan.durling.controllers;

import pij.ryan.durling.client.QuizClient;
import pij.ryan.durling.registry.Question;
import pij.ryan.durling.registry.Quiz;

public class QuizCreatorImpl implements QuizCreator {

    private final QuizClient quizClient;
    private Quiz quiz;

    public QuizCreatorImpl(QuizClient quizClient) {
        this.quizClient = quizClient;
    }

    @Override
    public int create(String name) throws IllegalArgumentException {
        if (inValid(name)) throw new IllegalArgumentException();
        quiz = quizClient.createQuiz(name);
        return quiz.getId();
    }

    @Override
    public Quiz getQuiz() {
        return quiz;
    }

    @Override
    public void addQuestion(Question question) throws IllegalArgumentException {
        if (inValid(question)) throw new IllegalArgumentException();
        quiz.addQuestion(question);
    }

    @Override
    public void save() {
        if (quiz != null) {
            quizClient.save(quiz);
        }
    }

    @Override
    public Question createQuestion(String question) throws IllegalArgumentException {
        if (inValid(question)) throw new IllegalArgumentException();
        return quizClient.createQuestion(question);
    }

    private boolean inValid(Question question) {
        return question == null;
    }
    private boolean inValid(String argument) {
        return argument == null || argument.trim().isEmpty();
    }
}