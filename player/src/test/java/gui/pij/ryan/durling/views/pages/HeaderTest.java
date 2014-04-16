package gui.pij.ryan.durling.views.pages;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.views.pages.Header;
import pij.ryan.durling.views.pages.HeaderImpl;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

public class HeaderTest extends GuiTest {

    private Header header;

    @Override
    protected Parent getRootNode() {
        header = new HeaderImpl();
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.BOTTOM_CENTER);
        stackPane.getChildren().add((Node) header);
        stackPane.setPrefHeight(700);
        stackPane.setPrefWidth(600);
        stackPane.setPadding(new Insets(50));
        return stackPane;
    }

    @Test
    public void shouldBeAbleToSetTheNameOfThePlayer() {
        Platform.setImplicitExit(false);
        String name = "David North";
        Platform.runLater(() -> header.setPlayerName(name));
        verifyThat("#" + ViewMessages.NAME_TITLE_ID, hasText(name));
    }

    @Test
    public void shouldBeAbleToSetTheTitleOfTheQuiz() {
        Platform.setImplicitExit(false);
        String name = "David Norths Plague";
        Platform.runLater(() -> header.setQuizTitle(name));
        verifyThat("#" + ViewMessages.QUIZ_TITLE_ID, hasText(name));
    }
}
