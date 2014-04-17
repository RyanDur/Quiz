package pij.ryan.durling.views.elements;

import javafx.scene.Node;

public interface ViewBox {
    void setMessage(String s);

    void removeMessage();

    void setView(Node node);

    void removeView();
}
