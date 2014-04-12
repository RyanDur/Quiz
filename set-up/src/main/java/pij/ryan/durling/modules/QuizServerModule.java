package pij.ryan.durling.modules;

import com.google.inject.AbstractModule;
import pij.ryan.durling.resources.ServerLink;
import pij.ryan.durling.resources.ServerLinkImpl;

public class QuizServerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ServerLink.class).to(ServerLinkImpl.class);
    }
}
