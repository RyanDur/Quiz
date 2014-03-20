package pij.ryan.durling.views;

import javafx.scene.Parent;
import javafx.scene.control.Button;

public class HomePage extends Parent {
    public HomePage() {
        Button button = new Button();
        button.setText("Add Quiz");
        button.setOnAction(actionEvent -> {
            System.out.println("Hello world");
        });
        this.setId("homePage");
        this.getChildren().add(button);
    } 
}