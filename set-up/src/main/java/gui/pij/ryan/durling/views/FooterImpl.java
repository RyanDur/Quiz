package gui.pij.ryan.durling.views;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class FooterImpl extends HBox implements Footer {

    private final Button saveButton;

    public FooterImpl() {
        this.setId("footer");
        this.getStylesheets().add("styles/footer.css");
        saveButton = getButton("Save", "save");
    }

    @Override
    public void addSaveButton() {
        this.getChildren().add(saveButton);
    }

    @Override
    public Button getSaveButton() {
        return saveButton;
    }

    private Button getButton(String label, String id) {
        Button button = new Button(label);
        button.setId(id);
        return button;
    }
}
