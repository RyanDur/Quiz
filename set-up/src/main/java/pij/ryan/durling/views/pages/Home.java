package pij.ryan.durling.views.pages;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class Home extends StackPane {

    public Home() {
        Button button = new Button();
        button.setText("Add Quiz");
        button.setOnAction(actionEvent -> {
            System.out.println("Hello world");
        });
        this.getChildren().add(button);
    }
}