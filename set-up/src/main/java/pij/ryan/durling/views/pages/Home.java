package pij.ryan.durling.views.pages;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class Home extends BorderPane {

    private HBox hBox;

    public Home() {
        this.setTop(addHBox(createQuiz()));
    }

    private HBox addHBox(Button button) {
        hBox = new HBox();
        hBox.setPadding(new Insets(15, 12, 15, 12));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #889499;");

        hBox.getChildren().add(button);
        return hBox;
    }

    private Button createQuiz() {
        Button button = new Button();
        button.setText("Add Quiz");
        button.setPrefSize(100, 20);
        button.setOnAction(actionEvent -> {
            button.setText("Create Quiz");
            addTextField();
        });
        return button;
    }

    private void addTextField() {
        TextField textField = new TextField();

        hBox.getChildren().add(textField);
    }
}