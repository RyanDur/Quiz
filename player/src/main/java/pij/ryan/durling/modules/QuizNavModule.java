package pij.ryan.durling.modules;

import com.google.inject.AbstractModule;
import pij.ryan.durling.controllers.QuizPlayer;
import pij.ryan.durling.controllers.QuizPlayerImpl;
import pij.ryan.durling.views.factories.Views;
import pij.ryan.durling.views.factories.ViewsImpl;

public class QuizNavModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new QuizPlayerModule());
        bind(QuizPlayer.class).to(QuizPlayerImpl.class);
        bind(Views.class).to(ViewsImpl.class);
    }
}
