package proxy;

import nodes.Node;
import protocols.TCPServer;
import protocols.UDP;

import java.util.ArrayList;

public class ProxyServer extends Thread {
    public void start() {
        UDP udp = new UDP();
        ArrayList<Node> nodes = udp.receiveInfoAboutRunningNodes();

        Node bestNode = selectBestNode(nodes);
        System.out.println("Best node " + bestNode);

        TCPServer tcpServer = new TCPServer(5555);
        tcpServer.acceptClients(bestNode);
    }

    private Node selectBestNode(ArrayList<Node> nodes) {
        ArrayList<Integer> links = new ArrayList<>();

        for (Node node : nodes) {
            links.add(node.getLinksNumber());
        }

        int maxLinks = getMaximalNumberOfLinks(links);

        Node bestNode = null;
        for (int j = 0; j < nodes.size(); j++) {
            if (maxLinks == nodes.get(j).getLinksNumber()) {
                bestNode = nodes.get(j);
            }
        }

        return bestNode;
    }

    private int getMaximalNumberOfLinks(ArrayList<Integer> links) {
        int maxLinks = 0;
        for (int i = 0; i < links.size(); i++) {
            if (maxLinks < links.get(i)) {
                maxLinks = links.get(i);
            }
        }

        return maxLinks;
    }
}