package pij.ryan.durling.views.elements;

import javafx.scene.Node;

public interface ViewBox {
    /**
     * Set the message in the box
     * @param s String
     */
    void setMessage(String s);

    /**
     * remove the message
     */
    void removeMessage();

    /**
     * Set the view
     * @param node The view
     */
    void setView(Node node);

    /**
     * remove the view
     */
    void removeView();
}
