package gui.pij.ryan.durling.views.pages;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.views.pages.QuestionsView;
import pij.ryan.durling.views.pages.QuestionsViewImpl;

import java.util.HashSet;
import java.util.Set;

public class QuestionsViewTest extends GuiTest {

    @Override
    protected Parent getRootNode() {
        Set<Question> questions = new HashSet<>();

        QuestionsView questionsView = new QuestionsViewImpl(questions);
        return (Parent) questionsView;
    }

    @Test
    public void should() {

    }
}
