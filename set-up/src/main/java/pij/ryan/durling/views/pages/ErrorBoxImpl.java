package pij.ryan.durling.views.pages;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pij.ryan.durling.messages.ViewMessages;

public class ErrorBoxImpl extends BorderPane implements ErrorBox {

    private final Label label;

    public ErrorBoxImpl() {
        this.getStylesheets().add(ViewMessages.ERROR_VIEW_STYLE_SHEET);
        HBox errors = new HBox();
        errors.setId(ViewMessages.ERROR_BOX_ID);
        label = new Label();
        label.setId(ViewMessages.ERROR_LABEL_ID);
        errors.setAlignment(Pos.CENTER);

        errors.getChildren().add(label);
        this.setTop(errors);
    }

    @Override
    public void setMessage(String message) {
        label.setText(message);
    }

    @Override
    public void removeMessage() {
        label.setText("");
    }
}
