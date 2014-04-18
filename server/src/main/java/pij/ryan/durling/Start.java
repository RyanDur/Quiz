package pij.ryan.durling;

import com.google.inject.Guice;
import com.google.inject.Injector;
import pij.ryan.durling.modules.ServerModule;
import pij.ryan.durling.resources.Server;
import pij.ryan.durling.resources.ServerImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Start {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ServerModule());
        Server server = injector.getInstance(ServerImpl.class);

        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Server", server);

            System.out.println("Server is ready.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
