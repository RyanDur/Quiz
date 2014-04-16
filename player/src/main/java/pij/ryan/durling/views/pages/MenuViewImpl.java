package pij.ryan.durling.views.pages;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import pij.ryan.durling.messages.ViewMessages;

public class MenuViewImpl extends ScrollPane implements MenuView {

    private GridPane gridPane;

    public MenuViewImpl() {
        this.getStylesheets().add(ViewMessages.MENU_VIEW_STYLE_SHEET);
        this.setId(ViewMessages.MENU_VIEW_ID);
        setGridOptions();
    }

    @Override
    public void addOption(Button button, int y) {
        gridPane.add(button,0,y);
    }

    private void setGridOptions() {
        gridPane = new GridPane();
        gridPane.setId(ViewMessages.MENU_VIEW_CHOICES_ID);
        gridPane.setAlignment(Pos.CENTER);
        this.setContent(gridPane);
    }
}
