package pij.ryan.durling.views.pages;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import pij.ryan.durling.messages.ViewMessages;


public class SignInViewImpl extends StackPane implements SignInView {

    private TextField field;
    private Button button;

    public SignInViewImpl() {
        this.getStylesheets().add(ViewMessages.SIGN_IN_VIEW_STYLE_SHEET);
        this.setId(ViewMessages.SIGN_IN_ID);
        this.getChildren().add(getSignIn());
    }

    @Override
    public String getName() {
        return field.getText();
    }

    @Override
    public Button getSignInButton() {
        return button;
    }

    private GridPane getSignIn() {
        GridPane gridPane = new GridPane();
        gridPane.setId(ViewMessages.SIGN_IN_GRID);
        gridPane.add(signInArea(), 0, 0);
        gridPane.add(signInButton(), 0, 1);
        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }

    private TextField signInArea() {
        field = new TextField();
        field.setId(ViewMessages.NAME_FIELD);
        field.setPromptText(ViewMessages.SIGN_IN_PROMPT_TEXT);
        return field;
    }

    private Button signInButton() {
        button = new Button();
        button.setText(ViewMessages.SIGN_IN_BUTTON);
        button.setId(ViewMessages.SIGN_IN_BUTTON_ID);
        return button;
    }
}
