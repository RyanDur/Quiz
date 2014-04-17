package pij.ryan.durling.views.elements;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import pij.ryan.durling.messages.ViewMessages;

public class ViewPaneImpl extends StackPane implements ViewPane {

    private final BorderPane borderPane;
    private final Label label;
    private Node node;

    public ViewPaneImpl() {
        this.getStylesheets().add(ViewMessages.VIEW_PANE_STYLESHEETS);
        this.setId(ViewMessages.VIEW_PANE_ID);

        borderPane = new BorderPane();
        borderPane.setId(ViewMessages.VIEW_BOX_ID);


        label = new Label();
        label.setId(ViewMessages.ERROR_LABEL);
        HBox errorBox = new HBox();
        errorBox.setId(ViewMessages.ERROR_BOX_ID);
        borderPane.setTop(errorBox);
        errorBox.setAlignment(Pos.CENTER);
        errorBox.getChildren().add(label);

        this.getChildren().add(borderPane);
    }

    @Override
    public void setView(Node node) {
        borderPane.setCenter(node);
    }

    @Override
    public void setMessage(String message) {
        label.setText(message);
    }
}
