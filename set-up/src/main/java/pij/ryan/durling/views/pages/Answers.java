package pij.ryan.durling.views.pages;

import javafx.scene.control.Button;

public interface Answers {

    /**
     * sets the question for the answer
     * @param questionLabel String
     */
    void setQuestionLabel(String questionLabel);

    /**
     * Ad answer button
     *
     * @return Button
     */
    Button getAddAnswerButton();

    /**
     * Add another question button
     *
     * @return Button
     */
    Button getAddAnotherQuestionButton();

    /**
     * Get he answer the user has written
     *
     * @return String
     */
    String getAnswer();

    /**
     * Get if the answer is correct
     *
     * @return boolean
     */
    boolean getAnswerValue();
}
