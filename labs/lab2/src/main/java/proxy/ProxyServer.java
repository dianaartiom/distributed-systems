package proxy;

import nodes.Node;
import protocols.UDP;

import java.util.ArrayList;

public class ProxyServer extends Thread {
    public void start() {
        UDP udp = new UDP();
        ArrayList<Node> nodes = udp.receiveInfoAboutRunningNodes();

        System.out.println("This is the best node " + nodes.get(0));

    }

}
