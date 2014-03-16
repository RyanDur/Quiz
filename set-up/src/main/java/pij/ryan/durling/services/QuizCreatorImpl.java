package pij.ryan.durling.services;

import pij.ryan.durling.servers.QuizServer;
import pij.ryan.durling.registry.Question;
import pij.ryan.durling.registry.Quiz;

public class QuizCreatorImpl implements QuizCreator {

    private final QuizServer quizServer;
    private Quiz quiz;

    public QuizCreatorImpl(QuizServer quizServer) {
        this.quizServer = quizServer;
    }

    @Override
    public int create(String name) throws IllegalArgumentException {
        if (inValid(name)) throw new IllegalArgumentException();
        quiz = quizServer.createQuiz(name);
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
            quizServer.save(quiz);
        }
    }

    @Override
    public Question createQuestion(String question) throws IllegalArgumentException {
        if (inValid(question)) throw new IllegalArgumentException();
        return quizServer.createQuestion(question);
    }

    private boolean inValid(Question question) {
        return question == null;
    }
    private boolean inValid(String argument) {
        return argument == null || argument.trim().isEmpty();
    }
}