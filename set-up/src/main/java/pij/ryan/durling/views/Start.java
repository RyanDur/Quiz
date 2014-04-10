package pij.ryan.durling.views;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pij.ryan.durling.controllers.QuizCreator;

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
//        Editor homePage = new Editor(quizCreator);
//
//        return new Scene(homePage, 500, 250);
        return null;
    }

}
