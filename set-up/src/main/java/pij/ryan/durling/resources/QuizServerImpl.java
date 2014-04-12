package pij.ryan.durling.resources;

import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;

public class QuizServerImpl implements QuizServer {
    @Override
    public Quiz createQuiz(String name) {
        return null;
    }

    @Override
    public Question createQuestion(String s, int score) {
        return null;
    }

    @Override
    public void save(Quiz quiz) {

    }

    @Override
    public Answer createAnswer(String answer, boolean value) {
        return null;
    }

    @Override
    public void lock(int quizId) {

    }
}
