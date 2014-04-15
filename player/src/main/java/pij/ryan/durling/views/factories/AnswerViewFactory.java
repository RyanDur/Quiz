package pij.ryan.durling.views.factories;

import javafx.scene.layout.GridPane;

import java.util.Set;

public interface AnswerViewFactory {
    javafx.scene.layout.StackPane getAnswerView(Set set);
}
