package pij.ryan.durling.views.pages;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Home extends VBox {

    private StackPane loader;

    public Home() {
        loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StackPane getPage() {
        return loader;
    }
}