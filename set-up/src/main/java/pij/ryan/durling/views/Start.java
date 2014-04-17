package pij.ryan.durling.views;


import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;
import pij.ryan.durling.modules.EditorModule;
import pij.ryan.durling.views.navigation.Editor;
import pij.ryan.durling.views.navigation.EditorImpl;

public class Start extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BasicConfigurator.configure();
        Injector injector = Guice.createInjector(new EditorModule());
        Editor editor = injector.getInstance(EditorImpl.class);
        stage.setScene(new Scene((Parent) editor, 625, 500));
        stage.show();
    }
}
