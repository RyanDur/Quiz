package pij.ryan.durling.modules;

import com.google.inject.AbstractModule;
import pij.ryan.durling.resources.QuizServer;
import pij.ryan.durling.resources.QuizServerImpl;

public class QuizPlayerModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new QuizServerModule());
        bind(QuizServer.class).to(QuizServerImpl.class);
    }
}
