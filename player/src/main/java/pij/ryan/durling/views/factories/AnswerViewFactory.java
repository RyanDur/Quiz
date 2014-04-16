package pij.ryan.durling.views.factories;

import javafx.scene.layout.GridPane;
import pij.ryan.durling.models.Answer;

import java.util.Set;

public interface AnswerViewFactory {
    GridPane getAnswerView(Set<Answer> set);
}
