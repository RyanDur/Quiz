package pij.ryan.durling.resources;

import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;
import pij.ryan.durling.models.QuizOption;

import java.util.Set;

public interface Server {
    Quiz createQuiz(String s);

    Question createQuestion(String question, int score);

    void save(Quiz quiz);

    Answer createAnswer(String answer, boolean value);

    void lock(int quizId);

    void unlock(int quizId);

    Set<QuizOption> getQuizOptions();

    Quiz getQuiz();

    boolean checkHighScore(Quiz quiz, String userName);
}
