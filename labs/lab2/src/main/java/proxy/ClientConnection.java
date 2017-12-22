package proxy;


import com.google.gson.Gson;
import common.Message;
import nodes.Node;
import protocols.TCPConnection;

import java.io.*;
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
            String clientRequest = clientTcpCommunication.receiveMessageInJson(clientSocket);
            System.out.println("Client Request is : " + clientRequest);

            Socket nodeSocket = new Socket(this.node.getLocation().getAddress(), this.node.getLocation().getPort());
            PrintWriter outNode = new PrintWriter(nodeSocket.getOutputStream(), true);
            outNode.println(clientRequest);

            BufferedReader inNode = new BufferedReader(new InputStreamReader(nodeSocket.getInputStream()));
            PrintWriter outClient = new PrintWriter(this.clientSocket.getOutputStream(), true);

            String input = inNode.readLine();

            Gson gson = new Gson();
            Message message = gson.fromJson(clientRequest, Message.class);
            outClient.println(input);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
