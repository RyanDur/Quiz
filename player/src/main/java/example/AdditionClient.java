package example;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AdditionClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1099;
        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            Addition addition = (Addition) registry.lookup("Addition");

            int answer = addition.add(3,5);
            System.out.println(answer + " Addition");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}