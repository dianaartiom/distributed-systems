package nodes;

import protocols.TCPServer;
import protocols.UDP;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class DataNode extends Thread {

    private Node node;

    public DataNode(Node node) {
        this.node = node;
    }
    public void run() {
        try {
            UDP udp = new UDP();

            this.node.setMessage("Message from node");
            udp.sendInfo(this.node);

            //Fake node connection
            ArrayList<InetSocketAddress> addresses = new ArrayList<>();
            InetSocketAddress inet1 = new InetSocketAddress("localhost", 8081);
            InetSocketAddress inet2 = new InetSocketAddress("localhost", 8082);
            addresses.add(inet1);
            addresses.add(inet2);

            // Fake more nodes list
            ArrayList<Node> nods = new ArrayList<>();
            nods.add(new Node(inet1));
            nods.add(new Node(inet2));

            this.node.setLinksAdresses(addresses);

            new Thread(() -> new TCPServer(this.node).start(nods)).start();
            Thread.sleep(200);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
