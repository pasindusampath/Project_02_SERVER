import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class User extends UnicastRemoteObject implements ChatClientInterface, Serializable {
    public String name;

    protected User() throws RemoteException {
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println(message);
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }
}
