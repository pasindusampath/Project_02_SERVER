import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClientInterface extends Remote {
    void receiveMessage(String message) throws RemoteException;
}
