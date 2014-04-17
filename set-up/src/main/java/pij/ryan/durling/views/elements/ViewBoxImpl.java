package pij.ryan.durling.views.elements;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import pij.ryan.durling.messages.ViewMessages;

public class ViewBoxImpl extends BorderPane implements ViewBox {

    private final Label label;
    private Node node;

    public ViewBoxImpl() {
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
        label.setTextFill(Color.RED);
    }

    @Override
    public void removeMessage() {
        label.setText("");
    }

    @Override
    public void setView(Node node) {
        this.node = node;
        this.setCenter(node);
    }

    @Override
    public void removeView() {
        this.getChildren().remove(node);
    }
}
