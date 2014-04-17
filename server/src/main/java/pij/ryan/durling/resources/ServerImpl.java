package pij.ryan.durling.resources;

import pij.ryan.durling.controllers.QuizCtrl;
import pij.ryan.durling.factories.QuizFactory;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;

import java.util.Set;

public class ServerImpl implements Server {
    private QuizCtrl quizCtrl;
    private QuizFactory quizFactory;

    public ServerImpl(QuizCtrl quizCtrl, QuizFactory quizFactory) {
        this.quizCtrl = quizCtrl;
        this.quizFactory = quizFactory;
    }

    @Override
    public Quiz createQuiz(String title) {
        return quizFactory.createQuiz(title);
    }

    @Override
    public Question createQuestion(String question, int score) {
        return quizFactory.createQuestion(question, score);
    }

    @Override
    public Answer createAnswer(String answer, boolean value) {
        return quizFactory.createAnswer(answer, value);
    }

    @Override
    public void save(Quiz quiz) {

    }

    @Override
    public Set<QuizOption> getQuizOptions() {
        return null;
    }

    @Override
    public Quiz getQuiz(int id) {
        return null;
    }

    @Override
    public boolean checkHighScore(Quiz quiz, int score) {
        return false;
    }

    @Override
    public void setHighScore(Quiz quiz, String playerName, int score) {

    }
}
