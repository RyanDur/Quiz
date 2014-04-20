package pij.ryan.durling.resources;

import com.google.inject.Inject;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ServerStartImpl implements ServerStart {

    private Server server;

    @Inject
    public ServerStartImpl(Server server) {
        this.server = server;
    }

    @Override
    public void registerServer() {
        System.setProperty("java.security.policy", "server/security.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Server", server);
            exitAction();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void exitAction() {
        System.out.println("Server is ready.\nPress Enter to save data and exit");
        new Scanner(System.in).nextLine();
        System.out.println("Saving Data");
        System.exit(0);
    }
}
