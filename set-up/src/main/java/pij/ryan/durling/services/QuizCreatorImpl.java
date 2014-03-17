package pij.ryan.durling.services;

import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;
import pij.ryan.durling.registry.Answer;
import pij.ryan.durling.registry.Question;
import pij.ryan.durling.registry.Quiz;
import pij.ryan.durling.resources.QuizServer;

public class QuizCreatorImpl implements QuizCreator {

    private final QuizServer quizServer;
    private Quiz quiz;

    public QuizCreatorImpl(QuizServer quizServer) {
        this.quizServer = quizServer;
    }

    @Override
    public int createQuiz(String name) throws IllegalArgumentException {
        if (inValid(name)) throw new IllegalArgumentException();
        quiz = quizServer.createQuiz(name);
        return quiz.getId();
    }

    @Override
    public Question createQuestion(String question, int value) throws IllegalArgumentException, IllegalQuizCreationException {
        if (inValid(question) || value < 1) throw new IllegalArgumentException();
        Quiz quiz = getQuiz();
        if (quiz == null) throw new IllegalQuizCreationException();
        return quiz.createQuestion(question, value);
    }

    @Override
    public Answer createAnswer(Question question, String answer, boolean value) {
        if (inValid(question) || inValid(answer)) throw new IllegalArgumentException();
        return question.createAnswer(answer, value);
    }

    @Override
    public Quiz getQuiz() {
        return quiz;
    }

    @Override
    public void save() throws IllegalQuizCreationException, InvalidQuizException {
        Quiz quiz = getQuiz();
        if (quiz == null) throw new IllegalQuizCreationException();
        if (quiz.isInValid()) throw new InvalidQuizException();
        quizServer.save(getQuiz());
    }

    private boolean inValid(Question question) {
        return question == null;
    }

    private boolean inValid(String argument) {
        return argument == null || argument.trim().isEmpty();
    }
}