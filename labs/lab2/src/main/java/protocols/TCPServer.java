package protocols;

import nodes.Node;
import proxy.ClientConnection;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPServer extends Thread {
    private ServerSocket serverSocket;
    private Node node;

    public TCPServer(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TCPServer(Node node) {
        this.node = node;
        try {
            this.serverSocket = new ServerSocket(this.node.getLocation().getPort());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void acceptClients(Node bestNode) {
        System.out.println("I am waiting for clients...");
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> new ClientConnection(clientSocket, bestNode).start()).start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void start(ArrayList<Node> nodes) {
        try {
            while (true) {
                ArrayList<String> messList = new ArrayList<>();

                Socket clientSocket = serverSocket.accept();
                System.out.println("You are connected to " + serverSocket.getLocalPort());

                TCPConnection clientTcpCommunication = new TCPConnection();
                String clientRequest = clientTcpCommunication.receiveMessage(clientSocket);
                System.out.println(clientRequest);

                for (Node node : nodes){
//                        TCPCommunication tcpCommunication = new TCPCommunication();
//                        tcpCommunication.startConnection(node.getLocation().getHostName(),node.getLocation().getPort());
                    messList.add(node.getMessage());
                }

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                System.out.println(messList);
                out.println(messList);


            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
