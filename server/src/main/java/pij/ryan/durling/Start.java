package pij.ryan.durling;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.log4j.BasicConfigurator;
import pij.ryan.durling.modules.ServerStartModule;
import pij.ryan.durling.resources.ServerStart;
import pij.ryan.durling.resources.ServerStartImpl;

public class Start {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        Injector injector = Guice.createInjector(new ServerStartModule());
        ServerStart server = injector.getInstance(ServerStartImpl.class);
        server.registerServer();
    }
}
