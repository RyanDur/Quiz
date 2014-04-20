package pij.ryan.durling.views.pages;

import javafx.scene.control.Button;

public interface Questions {

    /**
     * get the question button
     *
     * @return Button
     */
    Button getAddQuestionButton();

    /**
     * Get the Question
     *
     * @return String
     */
    String getQuestion();

    /**
     * get the score
     *
     * @return int
     */
    int getScore();
}
