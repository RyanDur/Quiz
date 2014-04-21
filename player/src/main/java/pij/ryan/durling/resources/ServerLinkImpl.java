package pij.ryan.durling.resources;

import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pij.ryan.durling.messages.ServerMessages;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Singleton
public class ServerLinkImpl implements ServerLink {

    private static final Logger log = LoggerFactory.getLogger(ServerLinkImpl.class);
    private Registry registry;

    public ServerLinkImpl()  {
        try {
            registry = LocateRegistry.getRegistry(ServerMessages.HOST, ServerMessages.PORT);
        } catch (RemoteException e) {
            log.error(ServerMessages.ERROR_MESSAGE);
        }
    }

    @Override
    public QuizMaster getQuizMaster()  {
        QuizMaster quizMaster = null;
        try {
            quizMaster = (QuizMaster) registry.lookup(ServerMessages.MASTER);
        } catch (RemoteException | NotBoundException e) {
            log.error(ServerMessages.ERROR_MESSAGE);
        }
        return quizMaster;
    }
}
