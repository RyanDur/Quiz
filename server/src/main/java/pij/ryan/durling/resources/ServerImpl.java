package pij.ryan.durling.resources;

import pij.ryan.durling.controllers.QuizCtrl;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;

import java.util.Set;

public class ServerImpl implements Server {
    private QuizCtrl quizCtrl;

    public ServerImpl(QuizCtrl quizCtrl) {
        this.quizCtrl = quizCtrl;
    }

    @Override
    public Quiz createQuiz(String title) {
        return quizCtrl.createQuiz(title);
    }

    @Override
    public Question createQuestion(String question, int score) {
        return quizCtrl.createQuestion(question, score);
    }

    @Override
    public Answer createAnswer(String answer, boolean value) {
        return quizCtrl.createAnswer(answer, value);
    }

    @Override
    public void save(Quiz quiz) {
        quizCtrl.add(quiz);
    }

    @Override
    public Set<QuizOption> getQuizOptions() {
        return quizCtrl.getQuizOptions();
    }

    @Override
    public Quiz getQuiz(int id) {
        return quizCtrl.getQuiz(id);
    }

    @Override
    public boolean checkHighScore(Quiz quiz, int score) {
        return false;
    }

    @Override
    public void setHighScore(Quiz quiz, String playerName, int score) {

    }
}
