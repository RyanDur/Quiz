package pij.ryan.durling;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.log4j.BasicConfigurator;
import pij.ryan.durling.modules.ServerModule;
import pij.ryan.durling.resources.Server;
import pij.ryan.durling.resources.ServerImpl;

public class Start {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        Injector injector = Guice.createInjector(new ServerModule());
        Server server = injector.getInstance(ServerImpl.class);
        server.registerServer();
    }
}
