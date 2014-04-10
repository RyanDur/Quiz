package pij.ryan.durling.views.pages;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public interface QuizView {

    Button getAddQuizButton();

    Button getCreateQuizButton();

    void setTitle(String name);

    TextField getQuizTitleField();

    String getTitle();
}
