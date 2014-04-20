package pij.ryan.durling.views.pages;

import javafx.scene.control.Button;

public interface SplitMenu {

    /**
     * add an option to the left menu
     *
     * @param button Button
     * @param y int
     */
    void addOptionLeft(Button button, int y);

    /**
     * add an option to the right menu
     *
     * @param button Button
     * @param y int
     */
    void addOptionRight(Button button, int y);
}
