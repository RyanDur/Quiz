package gui.pij.ryan.durling.views.pages;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.views.pages.Menu;
import pij.ryan.durling.views.pages.MenuImpl;
import pij.ryan.durling.views.pages.SplitMenu;
import pij.ryan.durling.views.pages.SplitMenuImpl;

public class SplitMenuTest extends GuiTest {

    private SplitMenu splitMenu;
    private Menu left;
    private Menu right;

    @Override
    protected Parent getRootNode() {
        left = new MenuImpl();
        right = new MenuImpl();
        splitMenu = new SplitMenuImpl(left, right);

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.BOTTOM_CENTER);
        stackPane.getChildren().add((Node) splitMenu);
        stackPane.setPrefHeight(700);
        stackPane.setPrefWidth(600);
        stackPane.setPadding(new Insets(50));
        return stackPane;
    }

    @Test
    public void should() {
        Button button1 = new Button("Hello");
        Button button2 = new Button("Hello");
        Button button3 = new Button("Hello");
        Button button4 = new Button("Hello");
        Button button5 = new Button("Hello");
        Button button6 = new Button("Hello");
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            splitMenu.addOptionLeft(button1, 0);
            splitMenu.addOptionLeft(button2, 1);
            splitMenu.addOptionLeft(button3, 2);

            splitMenu.addOptionRight(button4, 0);
            splitMenu.addOptionRight(button5, 1);
            splitMenu.addOptionRight(button6, 2);
        });
    }
}
