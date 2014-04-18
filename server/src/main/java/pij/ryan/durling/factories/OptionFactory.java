package pij.ryan.durling.factories;

import pij.ryan.durling.models.QuizOption;

public interface OptionFactory {
    QuizOption createQuizOption(int id, String name);
}
