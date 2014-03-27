package pij.ryan.durling.views;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.views.pages.Home;

public class Start extends Application {

    private final QuizCreator quizCreator;

    public Start(QuizCreator quizCreator) {
        this.quizCreator = quizCreator;
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
        Home homePage = new Home(quizCreator);

        return new Scene(homePage, 500, 250);
    }

}
