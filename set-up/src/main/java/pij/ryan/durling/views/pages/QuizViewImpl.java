package pij.ryan.durling.views.pages;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class QuizViewImpl extends HBox implements QuizView {

    private Button createQuizButton;
    private Button addQuizButton;
    private TextField addQuizField;

    public QuizViewImpl() {
        this.getStylesheets().add("styles/header.css");
        createQuizButton = getButton("Create Quiz", "create-quiz-button");
        addQuizButton = getButton("Add Quiz", "add-quiz-button");
        this.getChildren().add(addQuizButton);
        this.setId("header");
    }

    @Override
    public Button getAddQuizButton() {
        return addQuizButton;
    }

    @Override
    public Button getCreateQuizButton() {
        this.getChildren().remove(addQuizButton);
        this.getChildren().add(createQuizButton);
        return createQuizButton;
    }

    @Override
    public void setTitle(String name) {
        this.getChildren().removeAll(addQuizField, createQuizButton);
        Label title = new Label(name);
        title.setId("title");
        this.getChildren().add(title);
    }

    @Override
    public TextField getQuizTitleField() {
        addQuizField = new TextField();
        addQuizField.setId("create-quiz");
        this.getChildren().add(addQuizField);
        return addQuizField;
    }

    @Override
    public String getTitle() {
        return addQuizField.getText();
    }


    private Button getButton(String label, String id) {
        Button button = new Button(label);
        button.setId(id);
        return button;
    }
}
