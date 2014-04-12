package pij.ryan.durling.views;


import com.google.inject.Inject;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.views.pages.EditorImpl;
import pij.ryan.durling.views.pages.Views;

public class Start extends Application {

    private final QuizCreator quizCreator;
    private Views views;

    @Inject
    public Start(QuizCreator quizCreator, Views views) {
        this.quizCreator = quizCreator;
        this.views = views;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(getHomePage());
        stage.show();
    }

    public Scene getHomePage() {
        EditorImpl homePage = new EditorImpl(quizCreator, views);

        return new Scene(homePage, 500, 250);
    }

}
