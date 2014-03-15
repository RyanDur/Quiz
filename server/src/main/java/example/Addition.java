package example;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Addition extends Remote {
    public int add(int a, int b) throws RemoteException;
}
