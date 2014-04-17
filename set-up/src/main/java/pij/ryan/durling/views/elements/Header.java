package pij.ryan.durling.views.elements;

import javafx.scene.Node;
import javafx.scene.control.Button;

public interface Header {

    Button getCreateQuizButton();

    void setLockQuiz();

    Button getLockQuizButton();

    void toggleLock();

    void remove(Node node);

    String getTitle();

    void setTitle(String name);
}
