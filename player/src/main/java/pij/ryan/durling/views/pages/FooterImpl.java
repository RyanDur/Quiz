package pij.ryan.durling.views.pages;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pij.ryan.durling.messages.ViewMessages;

public class FooterImpl extends HBox implements Footer {

    private final BorderPane borderPane;

    public FooterImpl() {
        this.getStylesheets().add(ViewMessages.FOOTER_STYLESHEETS);
        this.setId(ViewMessages.FOOTER_ID);
        borderPane = new BorderPane();
        this.getChildren().add(borderPane);
    }

    @Override
    public void setSubmitButton(Button submitButton) {
        submitButton.setId(ViewMessages.SUBMIT_BUTTON_ID);
        BorderPane.setAlignment(submitButton, Pos.BOTTOM_RIGHT);
        borderPane.setLeft(submitButton);
    }
}
