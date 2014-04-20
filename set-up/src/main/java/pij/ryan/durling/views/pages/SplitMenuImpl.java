package pij.ryan.durling.views.pages;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import pij.ryan.durling.messages.ViewMessages;

public class SplitMenuImpl extends GridPane implements SplitMenu {

    private Menu left;
    private Menu right;

    public SplitMenuImpl(Menu left, Menu right) {
        this.getStylesheets().add(ViewMessages.SPLIT_MENU_STYLESHEETS);
        this.setId(ViewMessages.SPLIT_MENU_ID);
        this.setAlignment(Pos.CENTER);
        this.left = left;
        this.right = right;
        Label open = new Label(ViewMessages.OPEN);
        Label closed = new Label(ViewMessages.CLOSED);
        this.add(open, 0, 0);
        this.add(closed, 1, 0);
        this.add((Node) left, 0, 1);
        this.add((Node) right, 1, 1);
    }

    @Override
    public void addOptionLeft(Button button, int y) {
        left.addOption(button, y);
    }

    @Override
    public void addOptionRight(Button button, int y) {
        right.addOption(button, y);
    }
}
