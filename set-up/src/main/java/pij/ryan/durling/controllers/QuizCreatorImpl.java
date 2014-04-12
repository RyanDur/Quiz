package pij.ryan.durling.controllers;

import com.google.inject.Inject;
import pij.ryan.durling.exceptions.IllegalQuizCreationException;
import pij.ryan.durling.exceptions.InvalidQuizException;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.resources.QuizServer;

public class QuizCreatorImpl implements QuizCreator {

    private final QuizServer quizServer;
    private Quiz quiz;
    private Question question;

    @Inject
    public QuizCreatorImpl(QuizServer quizServer) {
        this.quizServer = quizServer;
    }

    @Override
    public String getName() {
        return quiz.getName();
    }

    @Override
    public void createQuiz(String name) throws IllegalArgumentException {
        if (inValid(name)) throw new IllegalArgumentException("Must specify a name for the quiz");
        quiz = quizServer.createQuiz(name);
    }

    @Override
    public void addQuestion(String questionString, int score) throws IllegalArgumentException, IllegalQuizCreationException {
        if (quiz == null) throw new IllegalQuizCreationException();
        if (score < 1) throw new IllegalArgumentException("Must have a score greater than zero");
        if (inValid(questionString)) throw new IllegalArgumentException("Must have a question");
        question = quizServer.createQuestion(questionString, score);
        quiz.add(question);
    }

    @Override
    public void addAnswer(String answerString, boolean value) throws IllegalArgumentException, IllegalQuizCreationException {
        if (quiz == null) throw new IllegalQuizCreationException();
        if (inValid(answerString)) throw new IllegalArgumentException("Must have an answer");
        Answer answer = quizServer.createAnswer(answerString, value);
        question.add(answer);
    }

    @Override
    public void save() throws IllegalQuizCreationException, InvalidQuizException {
        if (quiz == null) throw new IllegalQuizCreationException();
        if (!quiz.valid()) throw new InvalidQuizException();
        quizServer.save(quiz);
    }

    @Override
    public boolean validQuiz() {
        return quiz.valid();
    }

    @Override
    public String getQuestion() {
        return question.getQuestion();
    }

    @Override
    public void lockQuiz(int id) {
        quizServer.lock(id);
    }

    @Override
    public int getQuizId() {
        return quiz.getId();
    }

    private boolean inValid(String argument) {
        return argument == null || argument.trim().isEmpty();
    }
}