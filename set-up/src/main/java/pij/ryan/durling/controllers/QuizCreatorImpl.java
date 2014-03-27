package pij.ryan.durling.controllers;

import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.resources.QuizServer;

public class QuizCreatorImpl implements QuizCreator {

    private final QuizServer quizServer;
    private Quiz quiz;

    public QuizCreatorImpl(QuizServer quizServer) {
        this.quizServer = quizServer;
    }

    @Override
    public String getName() {
        return quiz.getName();
    }

    @Override
    public int createQuiz(String name) throws IllegalArgumentException {
        if (inValid(name)) throw new IllegalArgumentException("Must specify a name for the quiz");
        quiz = quizServer.createQuiz(name);
        return quiz.getId();
    }

    @Override
    public Question createQuestion(String question, int score) throws IllegalArgumentException, IllegalQuizCreationException {
        if (quiz == null) throw new IllegalQuizCreationException();
        if (score < 1) throw new IllegalArgumentException("Must have a score greater than zero");
        if (inValid(question)) throw new IllegalArgumentException("Must have a question");
        return quizServer.createQuestion(question, score);
    }

    @Override
    public Answer createAnswer(String answer, boolean value) throws IllegalArgumentException, IllegalQuizCreationException {
        if (quiz == null) throw new IllegalQuizCreationException();
        if (inValid(answer)) throw new IllegalArgumentException("Must have an answer");
        return quizServer.createAnswer(answer, value);
    }

    @Override
    public void addAnswer(Question question, Answer answer) throws IllegalArgumentException {
        if (question == null) throw new IllegalArgumentException("Invalid Question");
        question.add(answer);
    }

    @Override
    public void addQuestion(Question question) throws IllegalQuizCreationException, IllegalArgumentException {
        if (quiz == null) throw new IllegalQuizCreationException();
        if (inValid(question)) throw new IllegalArgumentException("Invalid Question");
        quiz.add(question);
    }

    @Override
    public void save() throws IllegalQuizCreationException, InvalidQuizException {
        if (quiz == null) throw new IllegalQuizCreationException();
        if (!quiz.valid()) throw new InvalidQuizException();
        quizServer.save(quiz);
    }

    private boolean inValid(Question question) {
        return question == null || !question.valid();
    }

    private boolean inValid(String argument) {
        return argument == null || argument.trim().isEmpty();
    }
}