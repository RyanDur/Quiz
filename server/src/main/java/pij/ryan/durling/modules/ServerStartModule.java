package pij.ryan.durling.modules;

import com.google.inject.AbstractModule;
import pij.ryan.durling.resources.Server;
import pij.ryan.durling.resources.ServerImpl;

public class ServerStartModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ServerModule());
        bind(Server.class).to(ServerImpl.class);
    }
}
