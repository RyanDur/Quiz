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
        if (inValid(name)) throw new IllegalArgumentException("Must specify a name for the quiz");
        quiz = quizServer.createQuiz(name);
        return quiz.getId();
    }

    @Override
    public Question createQuestion(String question, int score) throws IllegalArgumentException, IllegalQuizCreationException {
        Quiz quiz = getQuiz();
        if (quiz == null) throw new IllegalQuizCreationException();
        if (score < 1) throw new IllegalArgumentException("Must have a score greater than zero");
        if (inValid(question)) throw new IllegalArgumentException("Must have a question");
        return quiz.createQuestion(question, score);
    }

    @Override
    public Answer createAnswer(Question question, String answer, boolean value) throws IllegalArgumentException, IllegalQuizCreationException {
        Quiz quiz = getQuiz();
        if (quiz == null) throw new IllegalQuizCreationException();
        if (inValid(question)) throw new IllegalArgumentException("Invalid question");
        if (inValid(answer)) throw new IllegalArgumentException("Must have an answer");
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
        if (!quiz.valid()) throw new InvalidQuizException();
        quizServer.save(quiz);
    }

    private boolean inValid(Question question) {
        Quiz quiz = getQuiz();
        return question == null || !quiz.contains(question);
    }

    private boolean inValid(String argument) {
        return argument == null || argument.trim().isEmpty();
    }
}