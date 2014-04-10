package pij.ryan.durling.views.pages;

import javafx.scene.control.Button;

public interface AnswerView {
    void setQuestionLabel(String questionLabel);

    Button getAddAnswerButton();

    Button getAddAnotherQuestionButton();

    String getAnswer();

    boolean getAnswerValue();
}
