package pij.ryan.durling.resources;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface ServerLink {
    Server getServer() throws RemoteException, NotBoundException;
}
