package pij.ryan.durling.views;


import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pij.ryan.durling.modules.EditorModule;
import pij.ryan.durling.views.pages.Editor;
import pij.ryan.durling.views.pages.EditorImpl;

public class Start extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Injector injector = Guice.createInjector(new EditorModule());
        Editor editor = injector.getInstance(EditorImpl.class);

        stage.setScene((Scene) editor);
        stage.show();
    }
}
