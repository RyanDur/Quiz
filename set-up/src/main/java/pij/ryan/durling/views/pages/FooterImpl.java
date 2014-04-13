package pij.ryan.durling.views.pages;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import pij.ryan.durling.messages.ViewMessages;

public class FooterImpl extends HBox implements Footer {

    private final Button saveButton;

    public FooterImpl() {
        this.getStylesheets().add(ViewMessages.FOOTER_VIEW_STYLE_SHEET);
        this.setId(ViewMessages.FOOTER_VIEW_ID);
        saveButton = getButton(ViewMessages.SAVE_BUTTON, ViewMessages.SAVE_BUTTON_ID);
    }

    @Override
    public void addSaveButton() {
        this.getChildren().add(saveButton);
    }

    @Override
    public Button getSaveButton() {
        return saveButton;
    }

    private Button getButton(String label, String id) {
        Button button = new Button(label);
        button.setId(id);
        return button;
    }
}
