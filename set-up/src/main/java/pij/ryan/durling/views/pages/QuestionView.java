package pij.ryan.durling.views.pages;

import javafx.scene.control.Button;

public interface QuestionView {
    String getQuestion();

    int getScore();

    Button getAddQuestionButton();
}
