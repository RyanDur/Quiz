package pij.ryan.durling.views.pages;

import javafx.scene.control.RadioButton;

public interface Answer {
    /**
     * Add an answer
     *
     * @param radioButton RadioButton
     * @param answer String
     * @param y int
     */
    void addAnswer(RadioButton radioButton, String answer, int y);
}
