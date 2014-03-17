package pij.ryan.durling.services;

import pij.ryan.durling.registry.Answer;
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
    public void save() {
        if (quiz != null && quiz.isValid()) {
            quizServer.save(quiz);
        }
    }

    @Override
    public Question createQuestion(String question, int value) throws IllegalArgumentException {
        if (inValid(question) || value < 1) throw new IllegalArgumentException();

        return quiz.createQuestion(question, value);
    }

    @Override
    public Answer createAnswer(Question question, String answer, boolean value) {
        if (inValid(question) ||  inValid(answer)) throw new IllegalArgumentException();
        return question.createAnswer(answer, value);
    }

    private boolean inValid(Question question) {
        return question == null;
    }

    private boolean inValid(String argument) {
        return argument == null || argument.trim().isEmpty();
    }
}