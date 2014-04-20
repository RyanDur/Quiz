package pij.ryan.durling.views.elements;

import javafx.scene.Node;

public interface ViewPane {

    /**
     * Set the view
     *
     * @param node Node
     */
    void setView(Node node);

    /**
     * Set the message
     *
     * @param message String
     */
    void setMessage(String message);
}
