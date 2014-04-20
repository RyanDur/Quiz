package pij.ryan.durling.views.elements;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pij.ryan.durling.messages.ViewMessages;

public class HeaderImpl extends HBox implements Header {

    private Button createQuizButton;
    private Button addQuizButton;
    private TextField addQuizField;
    private BorderPane innerGrid;

    public HeaderImpl() {
        this.getStylesheets().add(ViewMessages.HEADER_VIEW_STYLE_SHEET);
        this.setId(ViewMessages.HEADER_VIEW_ID);
        innerGrid = new BorderPane();
        innerGrid.setId(ViewMessages.INNER_HEADER_VIEW_ID);
        this.getChildren().add(innerGrid);
        createQuizButton = getCreateQuizButton();
        addQuizButton();
    }

    @Override
    public Button getCreateQuizButton() {
        if (createQuizButton == null) {
            createQuizButton = getButton(ViewMessages.CREATE_QUIZ_BUTTON, ViewMessages.CREATE_QUIZ_BUTTON_ID);
        }
        return createQuizButton;
    }

    @Override
    public String getTitle() {
        return addQuizField.getText();
    }

    @Override
    public void setTitle(String name) {
        innerGrid.getChildren().removeAll(addQuizField, createQuizButton);
        Label title = new Label(name);
        title.setId(ViewMessages.TITLE_ID);
        innerGrid.setLeft(title);
    }

    public void addQuizButton() {
        addQuizButton = getButton(ViewMessages.ADD_QUIZ_BUTTON, ViewMessages.ADD_QUIZ_BUTTON_ID);
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
        addQuizField.setId(ViewMessages.CREATE_QUIZ_TITLE_FIELD_ID);
        innerGrid.setCenter(addQuizField);
        return addQuizField;
    }

    private Button getButton(String label, String id) {
        Button button = new Button(label);
        button.setId(id);
        return button;
    }

    private void remove(Node node) {
        innerGrid.getChildren().removeAll(node);
    }
}
