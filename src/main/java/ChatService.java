import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatService extends Remote {
    void sendMessage(String message, ChatClientInterface client) throws RemoteException;
    void registerClient(ChatClientInterface client) throws RemoteException;
    void unregisterClient(ChatClientInterface client) throws RemoteException;
}
