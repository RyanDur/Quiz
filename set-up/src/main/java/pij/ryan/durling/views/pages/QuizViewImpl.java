package pij.ryan.durling.views.pages;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class QuizViewImpl extends HBox implements QuizView {

    private Button createQuizButton;
    private Button addQuizButton;
    private TextField addQuizField;
    private Button lockQuizButton;
    private String lockQuiz = "Lock Quiz";
    private BorderPane innerGrid;

    public QuizViewImpl() {
        innerGrid = new BorderPane();
        innerGrid.setId("grid");
        this.getChildren().add(innerGrid);
        this.getStylesheets().add("styles/header.css");
        this.setId("header");
        lockQuizButton = getButton(lockQuiz, "lock");
        createQuizButton = getCreateQuizButton();
        addQuizButton();
    }

    @Override
    public Button getCreateQuizButton() {
        if (createQuizButton == null) {
            createQuizButton = getButton("Create Quiz", "create-quiz-button");
        }
        return createQuizButton;
    }

    @Override
    public void setLockQuiz() {
        innerGrid.getChildren().remove(createQuizButton);
        innerGrid.getChildren().remove(addQuizField);
        innerGrid.setRight(lockQuizButton);
    }

    @Override
    public Button getLockQuizButton() {
        return lockQuizButton;
    }

    @Override
    public void toggleLock() {
        if (lockQuizButton.getText().equals(lockQuiz)) {
            lockQuizButton.setText("Quiz Locked");
        } else {
            lockQuizButton.setText(lockQuiz);
        }
    }

    @Override
    public void remove(Node node) {
        innerGrid.getChildren().removeAll(node);
    }

    @Override
    public String getTitle() {
        return addQuizField.getText();
    }

    @Override
    public void setTitle(String name) {
        innerGrid.getChildren().removeAll(addQuizField, createQuizButton);
        Label title = new Label(name);
        title.setId("title");
        innerGrid.setLeft(title);
    }

    public void addQuizButton() {
        addQuizButton = getButton("Add Quiz", "add-quiz-button");
        addQuizButton.setOnAction(e -> {
            remove(addQuizButton);
            addQuizTitleField();
            addCreateQuizButton();
        });
        innerGrid.setLeft(addQuizButton);
    }

    private void addCreateQuizButton() {
        innerGrid.setLeft(createQuizButton);
    }

    private TextField addQuizTitleField() {
        addQuizField = new TextField();
        addQuizField.setId("create-quiz");
        innerGrid.setCenter(addQuizField);
        return addQuizField;
    }


    private Button getButton(String label, String id) {
        Button button = new Button(label);
        button.setId(id);
        return button;
    }
}
