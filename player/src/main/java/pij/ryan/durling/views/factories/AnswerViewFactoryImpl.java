package pij.ryan.durling.views.factories;

import javafx.scene.layout.GridPane;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.views.pages.AnswerViewImpl;

import java.util.Set;

public class AnswerViewFactoryImpl implements AnswerViewFactory {
    @Override
    public GridPane getAnswerView(Set<Answer> set) {
        return new AnswerViewImpl(set);
    }
}
