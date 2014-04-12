package pij.ryan.durling.modules;

import com.google.inject.AbstractModule;
import pij.ryan.durling.controllers.QuizCreator;
import pij.ryan.durling.controllers.QuizCreatorImpl;
import pij.ryan.durling.views.pages.Views;
import pij.ryan.durling.views.pages.ViewsImpl;

public class EditorModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new QuizCreatorModule());
        bind(QuizCreator.class).to(QuizCreatorImpl.class);
        bind(Views.class).to(ViewsImpl.class);
    }
}
