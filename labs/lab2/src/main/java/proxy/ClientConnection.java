package proxy;

import nodes.Node;
import protocols.TCPConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection extends Thread {
    private Socket clientSocket;
    private Node node;

    public ClientConnection(Socket clientSocket, Node node) {
        this.clientSocket = clientSocket;
        this.node = node;
    }


    @Override
    public synchronized void start() {
        try {
            TCPConnection clientTcpCommunication = new TCPConnection();

            String clientRequest = clientTcpCommunication.receiveMessage(clientSocket);
            System.out.println("Client Request is : " + clientRequest);

            Socket nodeSocket = new Socket(this.node.getLocation().getAddress(), this.node.getLocation().getPort());
            PrintWriter outNode = new PrintWriter(nodeSocket.getOutputStream(), true);
            outNode.println(clientRequest + "new string");

            BufferedReader inNode = new BufferedReader(new InputStreamReader(nodeSocket.getInputStream()));
            PrintWriter outClient = new PrintWriter(this.clientSocket.getOutputStream(), true);

            String input = inNode.readLine();
            outClient.write(input);
            System.out.println("input" + input);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
