package pij.ryan.durling.factories;

import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.Quiz;

public interface QuizFactory {
    Quiz createQuiz(String title);

    Question createQuestion(String question, int score);

    Answer createAnswer(String answer, boolean value);
}
