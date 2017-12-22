package proxy;

import nodes.Node;
import protocols.TCPServer;
import protocols.UDP;

import java.util.ArrayList;

public class ProxyServer extends Thread {
    public void start() {
        UDP udp = new UDP();
        ArrayList<Node> nodes = udp.receiveInfoAboutRunningNodes();

        for (Node node :
                nodes) {
            System.out.println(node);
        }

        // Computing the best node
        Node bestNode = selectBestNode(nodes);
        TCPServer tcpServer = new TCPServer(5555);
        tcpServer.acceptClients(bestNode);
    }

    private Node selectBestNode(ArrayList<Node> nodes) {
        Node bestNode = nodes.get(0);
        return bestNode;
    }

}
