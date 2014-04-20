package pij.ryan.durling.resources;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

@Singleton
public class ServerImpl implements Server {

    private QuizMaker quizMaker;
    private QuizMaster quizMaster;

    @Inject
    public ServerImpl(QuizMaker quizMaker, QuizMaster quizMaster) {
        this.quizMaker = quizMaker;
        this.quizMaster = quizMaster;
    }

    @Override
    public void registerServer() {
        System.setProperty("java.security.policy", "server/security.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("QuizMaker", quizMaker);
            registry.rebind("QuizMaster", quizMaster);
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
