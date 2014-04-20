package pij.ryan.durling.resources;

import pij.ryan.durling.models.QuizOption;

import java.util.Set;

public interface QuizMaker {
    int createQuiz(String title);

    void addQuestion(String question, int score);

    void addAnswer(String answer, boolean value);

    Set<QuizOption> getAvailableQuizzes();

    Set<QuizOption> getClosedQuizzes();

    void save();

    boolean validQuiz();

    boolean empty();

    boolean validQuestion();

    void closeQuiz(int quizId);

    void openQuiz(int quizId);
}
