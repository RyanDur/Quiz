package pij.ryan.durling.views.pages;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import pij.ryan.durling.controllers.QuizCreator;

public class QuizViewImpl extends HBox implements QuizView {

    private Button createQuizButton;
    private Button addQuizButton;
    private TextField addQuizField;
    private QuizCreator quizCreator;
    private Button lockQuizButton;
    private String lockQuiz = "Lock Quiz";
    private final GridPane innerGrid;

    public QuizViewImpl(QuizCreator quizCreator) {
        innerGrid = new GridPane();
        this.getChildren().add(innerGrid);
        this.quizCreator = quizCreator;
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
        remove(createQuizButton);
        remove(addQuizField);
        innerGrid.add(lockQuizButton, 3, 0);
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

    public void addQuizButton() {
        addQuizButton = getButton("Add Quiz", "add-quiz-button");
        addQuizButton.setOnAction(e -> {
            remove(addQuizButton);
            addQuizTitleField();
            addCreateQuizButton();
        });
        innerGrid.add(addQuizButton, 0, 0);
    }

    @Override
    public void remove(Node node) {
        innerGrid.getChildren().remove(node);
    }

    private void addCreateQuizButton() {
        createQuizButton.setOnAction(event -> {
            quizCreator.createQuiz(getTitle());
            setTitle(quizCreator.getName());
        });
        this.getChildren().add(createQuizButton);
    }

    private void setTitle(String name) {
        innerGrid.getChildren().removeAll(addQuizField, createQuizButton);
        Label title = new Label(name);
        title.setId("title");
        innerGrid.add(title, 0, 0);
    }

    private TextField addQuizTitleField() {
        addQuizField = new TextField();
        addQuizField.setId("create-quiz");
        innerGrid.add(addQuizField, 1, 0);
        return addQuizField;
    }

    private String getTitle() {
        return addQuizField.getText();
    }


    private Button getButton(String label, String id) {
        Button button = new Button(label);
        button.setId(id);
        return button;
    }
}
