package pij.ryan.durling.views.pages;

import javafx.scene.Node;
import javafx.scene.control.Button;

public interface QuizView {

    Button getCreateQuizButton();

    void setLockQuiz();

    Button getLockQuizButton();

    void toggleLock();

    void remove(Node node);
}
