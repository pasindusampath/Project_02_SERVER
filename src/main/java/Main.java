import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) {
        try {
            ChatServer server = new ChatServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ChatService", server);

            System.out.println("Server started.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
