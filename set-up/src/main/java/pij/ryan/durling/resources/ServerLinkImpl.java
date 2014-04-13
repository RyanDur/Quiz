package pij.ryan.durling.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerLinkImpl implements ServerLink {

    private static final Logger log = LoggerFactory.getLogger(ServerLinkImpl.class);
    private Registry registry;

    public ServerLinkImpl()  {
        try {
            registry = LocateRegistry.getRegistry("localhost", 1099);
        } catch (RemoteException e) {
            log.info("Server is not running");
        }
    }

    @Override
    public Server getServer()  {
        Server server = null;
        try {
            server = (Server) registry.lookup("Server");
        } catch (RemoteException | NotBoundException e) {
            log.info("Server is not running");
        }
        return server;
    }
}
