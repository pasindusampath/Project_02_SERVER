import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatServer extends UnicastRemoteObject implements ChatService {
    private List<ChatClientInterface> clients;

    public ChatServer() throws RemoteException {
        clients = new ArrayList<>();
    }

    @Override
    public void sendMessage(String message, ChatClientInterface client) throws RemoteException {
        String fullMessage = "Client: " + message;
        System.out.println(fullMessage);

        // Broadcast the message to all clients
        for (ChatClientInterface c : clients) {
            c.receiveMessage(fullMessage);
        }
    }

    @Override
    public void registerClient(ChatClientInterface client) throws RemoteException {
        clients.add(client);
        System.out.println("New client registered.");
        System.out.println(client.getName());

        clients.forEach((e)->{
            try {
                System.out.println(e.getName());
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        });

    }

    @Override
    public void unregisterClient(ChatClientInterface client) throws RemoteException {
        clients.remove(client);
        System.out.println("Client unregistered.");
    }
}
