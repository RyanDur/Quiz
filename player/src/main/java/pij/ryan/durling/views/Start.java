package pij.ryan.durling.views;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;
import pij.ryan.durling.modules.QuizPlayerViewModule;
import pij.ryan.durling.views.pages.QuizPlayerView;
import pij.ryan.durling.views.pages.QuizPlayerViewImpl;

public class Start extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BasicConfigurator.configure();
        Injector injector = Guice.createInjector(new QuizPlayerViewModule());
        QuizPlayerView quizPlayerView = injector.getInstance(QuizPlayerViewImpl.class);
        stage.setScene(new Scene((Parent) quizPlayerView, 600, 800));
        stage.show();
    }
}
