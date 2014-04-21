package pij.ryan.durling.resources;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import pij.ryan.durling.messages.ServerMessages;

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
        System.setProperty(ServerMessages.JAVA_PROPERTY, ServerMessages.POLICY);
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            Registry registry = LocateRegistry.createRegistry(ServerMessages.PORT);
            registry.rebind(ServerMessages.MAKER, quizMaker);
            registry.rebind(ServerMessages.MASTER, quizMaster);
            exitAction();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void exitAction() {
        System.out.println(ServerMessages.SERVER_READY);
        new Scanner(System.in).nextLine();
        System.out.println(ServerMessages.EXIT);
        System.exit(0);
    }
}
