package example;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AdditionServer {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Addition", new AdditionImpl());

            System.out.println("Addition Server is ready.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
