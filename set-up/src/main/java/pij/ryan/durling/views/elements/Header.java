package pij.ryan.durling.views.elements;

import javafx.scene.control.Button;

public interface Header {

    /**
     * Get the create quiz button
     *
     * @return Button
     */
    Button getCreateQuizButton();

    /**
     * get the title
     * @return String
     */
    String getTitle();

    /**
     * Set the title
     * @param name String
     */
    void setTitle(String name);
}
