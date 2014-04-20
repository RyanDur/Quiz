package pij.ryan.durling;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.log4j.BasicConfigurator;
import pij.ryan.durling.modules.ServerModule;
import pij.ryan.durling.resources.Server;
import pij.ryan.durling.resources.ServerImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Start {

    public static void main(String[] args) {

        BasicConfigurator.configure();
        Injector injector = Guice.createInjector(new ServerModule());
        Server server = injector.getInstance(ServerImpl.class);

        System.setProperty("java.security.policy", "server/security.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Server", server);

            System.out.println("Server is ready.\nPress Enter to exit");
            new Scanner(System.in).nextLine();
            System.out.println("Saving Data");
            System.exit(0);
            System.out.println("Data Save");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
