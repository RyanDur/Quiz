package pij.ryan.durling.views.pages;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import pij.ryan.durling.controllers.Menu;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.models.QuizOption;

import java.util.Set;

public class MenuViewImpl extends ScrollPane implements MenuView {

    private Set<QuizOption> options;
    private GridPane gridPane;
    private int quizId;
    private Group group;
    private Header header;

    public MenuViewImpl(Menu menu, Header header) {
        this.header = header;
        this.getStylesheets().add(ViewMessages.MENU_VIEW_STYLE_SHEET);
        this.setId(ViewMessages.MENU_VIEW_ID);
        group = new Group();
        setGridOptions(menu);
        addQuizOptions();
    }

    @Override
    public int getChosenQuizId() {
        return quizId;
    }


    @Override
    public Group getButtonGroup() {
        return group;
    }

    private void addQuizOptions() {
        int y = 0;
        for (QuizOption quizOption : options) {
            Button button = getButton(quizOption.getQuizTitle(), y);

            button.setOnAction(e -> {
                quizId = quizOption.getQuizId();
                header.setQuizTitle(quizOption.getQuizTitle());
            });
            group.getChildren().add(button);
            gridPane.add(button,0,y);
            y++;
        }
    }

    private void setGridOptions(Menu menu) {
        options = menu.getQuizzes();
        gridPane = new GridPane();
        gridPane.setId(ViewMessages.MENU_VIEW_CHOICES_ID);
        gridPane.setAlignment(Pos.CENTER);
        this.setContent(gridPane);
    }

    private Button getButton(String name, int id) {
        Button button = new Button();
        button.setText(name);
        button.setId(ViewMessages.MENU_VIEW_BUTTON_ID + id);
        return button;
    }
}
