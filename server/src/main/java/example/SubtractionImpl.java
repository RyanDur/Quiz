package example;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SubtractionImpl extends UnicastRemoteObject implements Subtraction {
    public SubtractionImpl() throws RemoteException {}

    @Override
    public int subtract(int a, int b) throws RemoteException {
        return a - b;
    }
}
