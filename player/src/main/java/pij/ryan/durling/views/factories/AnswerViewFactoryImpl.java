package pij.ryan.durling.views.factories;

import javafx.scene.layout.GridPane;
import pij.ryan.durling.views.pages.AnswerViewImpl;

import java.util.Set;

public class AnswerViewFactoryImpl implements AnswerViewFactory {
    @Override
    public GridPane getAnswerView(Set set) {
        return new AnswerViewImpl(set);
    }
}
