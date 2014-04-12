package pij.ryan.durling.resources;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerLinkImpl implements ServerLink {

    private final Registry registry;

    public ServerLinkImpl() throws RemoteException {
        registry = LocateRegistry.getRegistry("localhost", 1099);
    }

    @Override
    public Server getServer() throws RemoteException, NotBoundException {
        return (Server) registry.lookup("Server");
    }
}
