package pij.ryan.durling.factories;

import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.models.QuizOptionImpl;

public class OptionFactoryImpl implements OptionFactory {
    @Override
    public QuizOption createQuizOption(int id, String name) {
        return new QuizOptionImpl(name, id);
    }
}
