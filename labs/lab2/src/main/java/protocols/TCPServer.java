package protocols;

import common.Command;
import common.ECommand;
import common.Employee;
import common.Message;
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
                ArrayList<Employee> employees = new ArrayList<>();

                Socket clientSocket = serverSocket.accept();
                System.out.println("connected to " + serverSocket.getLocalPort());

                TCPConnection clientTcpCommunication = new TCPConnection();
                Message clientRequest = clientTcpCommunication.receiveMessage(clientSocket);
                System.out.println(clientRequest);

                for (Node node : nodes){
                    employees.addAll(node.getEmployees());
                }

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                Command response = new Command();

                employees.addAll(this.node.getEmployees());

                String message = null;
                if (clientRequest.getCommand().equals(ECommand.GET_ALL)) {
                    message = response.getAll(employees);
                } else if (clientRequest.getCommand().equals(ECommand.SORT)) {
                    message = response.getSortedEmployees(employees, clientRequest);
                }

                System.out.println(message);
                out.println(message);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
