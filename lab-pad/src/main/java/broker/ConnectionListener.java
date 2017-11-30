package broker;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class ConnectionListener {
    private Consumer<Client> onConnectorAccepted;
    private ServerSocket serverSocket;

    public ConnectionListener(InetSocketAddress address) {
        initServerSocket(address);
    }

    public void start(){
        new Thread(this::listenForConnections).start();
    }

    private void listenForConnections() {
       while(true){
           try {
               Socket clientSocket = serverSocket.accept();
               Client client = new Client(clientSocket);
               if (onConnectorAccepted != null){
                   onConnectorAccepted.accept(client);
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }

    private void initServerSocket(InetSocketAddress address) {
        try {
            this.serverSocket = new ServerSocket();
            this.serverSocket.bind(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOnConnectorAccepted(Consumer<Client> onConnectorAccepted) {
        this.onConnectorAccepted = onConnectorAccepted;
    }
}
