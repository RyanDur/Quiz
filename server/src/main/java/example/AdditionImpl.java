package example;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AdditionImpl extends UnicastRemoteObject implements Addition {

    public AdditionImpl() throws RemoteException {}

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
