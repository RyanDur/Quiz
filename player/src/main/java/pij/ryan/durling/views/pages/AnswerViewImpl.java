package pij.ryan.durling.views.pages;

import javafx.scene.layout.GridPane;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.views.pages.AnswerView;

import java.util.Set;

public class AnswerViewImpl extends GridPane implements AnswerView {
    private Set<Answer> answers;

    public AnswerViewImpl(Set<Answer> answers) {
        this.answers = answers;
    }
}
