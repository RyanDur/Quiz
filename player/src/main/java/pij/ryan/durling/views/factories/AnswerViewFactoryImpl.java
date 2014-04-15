package pij.ryan.durling.views.factories;

import javafx.scene.layout.StackPane;
import pij.ryan.durling.views.pages.AnswerViewImpl;

import java.util.Set;

public class AnswerViewFactoryImpl implements AnswerViewFactory {
    @Override
    public StackPane getAnswerView(Set set) {
        return new AnswerViewImpl(set);
    }
}
