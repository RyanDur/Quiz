package pij.ryan.durling.views.pages;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class Home extends BorderPane {

    private HBox hBox;
    private Button addQuizbutton;
    private GridPane grid;

    public Home() {
        this.setTop(header(createQuiz()));
        this.setCenter(addGrid());
    }

    private HBox header(Button button) {
        hBox = new HBox();
        hBox.setPadding(new Insets(15, 12, 15, 12));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #889499;");

        hBox.getChildren().add(button);
        return hBox;
    }

    private Button createQuiz() {
        addQuizbutton = new Button();
        addQuizbutton.setText("Add Quiz");
        addQuizbutton.setPrefSize(100, 20);
        addQuizbutton.setOnAction(actionEvent -> {
            addTextField();
        });
        return addQuizbutton;
    }

    private void addTextField() {
        TextField textField = new TextField();
        Button createQuizButton = new Button();
        createQuizButton.setText("Create Quiz");
        createQuizButton.setPrefSize(100, 20);
        createQuizButton.setOnAction(actionEvent -> {
            addQuestionCreator();
        });
        hBox.getChildren().remove(addQuizbutton);
        hBox.getChildren().add(createQuizButton);
        hBox.getChildren().add(textField);
    }

    private GridPane addGrid() {
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 12, 50, 12));

        return grid;
    }

    private void addQuestionCreator() {
        TextField textField = new TextField();

        textField.setPromptText("Add Question");
        textField.setId("add-question");
        grid.add(textField, 1, 0);
    }
}